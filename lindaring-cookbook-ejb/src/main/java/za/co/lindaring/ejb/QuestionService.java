package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Question;
import za.co.lindaring.entity.Question_;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.exception.TechnicalException;
import za.co.lindaring.types.QuestionLookUp;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
@Stateless
@LocalBean
public class QuestionService extends BaseService {

    @EJB
    private MessageService messageService;

    public Question getQuestion(long id) {
        return getEntityManager().find(Question.class, id);
    }

    public List<Long> getAllQuestionIds() {
        List<Question> questions = getAllQuestions();
        List<Long> ids = new ArrayList<>();
        for (Question q: questions) {
            ids.add(q.getId());
        }
        return ids;
    }

    public List<Question> getAllQuestions() {
        TypedQuery<Question> result = getEntityManager().createNamedQuery("Question.findAll", Question.class);
        return result.getResultList();
    }

    public SortedMap<Integer, Long> getAllQuestionsGroupByMonthAdded() {
        List<Question> questions = getAllQuestions();
        SortedMap<Integer, Long> map = new TreeMap<>();

        for (Question q: questions)
            incrementMonthValue(map, q.getDateAdded());

        return map;
    }

    public List<Question> searchQuestion(QuestionLookUp questionLookUp) throws TechnicalException, BusinessException {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

            CriteriaQuery<Question> query = cb.createQuery(Question.class);
            Root<Question> root = query.from(Question.class);
            Predicate predicate = getSearchQuestionPredicate(cb, root, questionLookUp);

            query.select(root).where(predicate);
            TypedQuery<Question> typedQuery = getEntityManager().createQuery(query);
            return typedQuery.getResultList();

        } catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            throw new TechnicalException("Search question failed", e);
        }
    }

    private Predicate getSearchQuestionPredicate(CriteriaBuilder cb, Root<Question> root, QuestionLookUp lookUp)
            throws BusinessException {
        final List<Predicate> orPredicates = new ArrayList<>();
        if (lookUp != null) {
            setQuestionDescriptionPredicate(orPredicates, cb, root, lookUp);
            setQuestionStartAndEndDate(orPredicates, cb, root, lookUp);
            setQuestionActivePredicate(orPredicates, cb, root, lookUp);
        }
        return cb.and(orPredicates.toArray(new Predicate[orPredicates.size()]));
    }

    private void setQuestionDescriptionPredicate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Question> root, QuestionLookUp lookUp) {
        if (!StringUtils.isBlank(lookUp.getName())) {
            predicateList.add(cb.or(cb.like(root.get(Question_.desc),
                    "%" + lookUp.getName() + "%")));
        }
    }

    private void setQuestionStartAndEndDate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Question> root, QuestionLookUp lookUp)
            throws BusinessException {
        if (lookUp.getStartDate() != null && lookUp.getEndDate() != null) {
            predicateList.add(cb.or(cb.greaterThan(root.get(Question_.dateAdded), lookUp.getStartDate())));
            predicateList.add(cb.or(cb.lessThan(root.get(Question_.dateAdded), lookUp.getEndDate())));
        } else if (lookUp.getStartDate() != null && lookUp.getEndDate() == null) {
            throw new BusinessException(messageService.getEndDateNotEnteredMessage());
        } else if (lookUp.getStartDate() == null && lookUp.getEndDate() != null) {
            throw new BusinessException(messageService.getStartDateNotEnteredMessage());
        }
    }

    private void setQuestionActivePredicate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Question> root, QuestionLookUp lookUp) {
        if (lookUp.getActive() != null) {
            predicateList.add(cb.or(cb.equal(root.get(Question_.active), lookUp.getActive())));
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveQuestion(Question question) {
        getEntityManager().merge(question);
        getEntityManager().flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertQuestion(Question question) {
        getEntityManager().persist(question);
        getEntityManager().flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteQuestion(long id) {
        Question question = getQuestion(id);
        getEntityManager().remove(question);
    }

}
