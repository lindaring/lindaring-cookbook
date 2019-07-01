package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.entity.Answer_;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.exception.TechnicalException;
import za.co.lindaring.types.AnswerLookUp;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
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
public class AnswerService extends BaseService {

    @EJB
    private MessageService messageService;

    public Answer getAnswer(long id) {
        return getEntityManager().find(Answer.class, id);
    }

    public List<Answer> getAllAnswers() {
        TypedQuery<Answer> result = getEntityManager().createNamedQuery("Answer.findAll", Answer.class);
        return result.getResultList();
    }

    public SortedMap<Integer, Long> getAllAnswersGroupByMonthAdded() {
        List<Answer> answers = getAllAnswers();
        SortedMap<Integer, Long> map = new TreeMap<>();

        for (Answer a: answers)
            incrementMonthValue(map, a.getDateAdded());

        return map;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAnswer(long id) {
        Query query = getEntityManager().createNamedQuery("Answer.deleteById");
        query.setParameter("answerId", id);
        query.executeUpdate();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertAnswer(Answer answer) throws TechnicalException {
        try {
            answer.setId(null);
            getEntityManager().persist(answer);
            getEntityManager().flush();

        } catch (Exception e) {
            throw new TechnicalException("Insert answer failed", e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveAnswer(Answer answer) {
        getEntityManager().merge(answer);
        getEntityManager().flush();
    }

    public List<Answer> searchAnswer(AnswerLookUp answerLookUp) throws BusinessException, TechnicalException {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

            CriteriaQuery<Answer> query = cb.createQuery(Answer.class);
            Root<Answer> root = query.from(Answer.class);
            Predicate predicate = getSearchAnswerPredicate(cb, root, answerLookUp);

            query.select(root).where(predicate);
            TypedQuery<Answer> typedQuery = getEntityManager().createQuery(query);
            return typedQuery.getResultList();

        } catch (BusinessException e) {
            throw e;

        } catch (Exception e) {
            throw new TechnicalException("Search question failed", e);
        }
    }

    private Predicate getSearchAnswerPredicate(CriteriaBuilder cb, Root<Answer> root, AnswerLookUp lookUp)
            throws BusinessException {
        final List<Predicate> predicateList = new ArrayList<>();
        if (lookUp != null) {
            setAnswerTextPredicate(predicateList, cb, root, lookUp);
            setAnswerStartAndEndDate(predicateList, cb, root, lookUp);
            setAnswerActivePredicate(predicateList, cb, root, lookUp);
            setAnswerPointsPredicate(predicateList, cb, root, lookUp);
            setAnswerQuestionIdPredicate(predicateList, cb, root, lookUp);
        }
        return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }

    private void setAnswerTextPredicate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Answer> root, AnswerLookUp lookUp) {
        if (!StringUtils.isBlank(lookUp.getText())) {
            predicateList.add(cb.or(cb.like(root.get(Answer_.text),
                    "%" + lookUp.getText() + "%")));
        }
    }

    private void setAnswerStartAndEndDate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Answer> root, AnswerLookUp lookUp)
            throws BusinessException {
        if (lookUp.getStartDate() != null && lookUp.getEndDate() != null) {
            predicateList.add(cb.or(cb.greaterThanOrEqualTo(root.get(Answer_.dateAdded), lookUp.getStartDate())));
            predicateList.add(cb.or(cb.lessThanOrEqualTo(root.get(Answer_.dateAdded), lookUp.getEndDate())));
        } else if (lookUp.getStartDate() != null && lookUp.getEndDate() == null) {
            throw new BusinessException(messageService.getEndDateNotEnteredMessage());
        } else if (lookUp.getStartDate() == null && lookUp.getEndDate() != null) {
            throw new BusinessException(messageService.getStartDateNotEnteredMessage());
        }
    }

    private void setAnswerActivePredicate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Answer> root, AnswerLookUp lookUp) {
        if (lookUp.getActive() != null) {
            predicateList.add(cb.or(cb.equal(root.get(Answer_.active), lookUp.getActive())));
        }
    }

    private void setAnswerPointsPredicate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Answer> root, AnswerLookUp lookUp) {
        if (lookUp.getPoints() != null && lookUp.getPoints() > 0) {
            predicateList.add(cb.or(cb.equal(root.get(Answer_.points), lookUp.getPoints())));
        }
    }

    private void setAnswerQuestionIdPredicate(List<Predicate> predicateList, CriteriaBuilder cb, Root<Answer> root, AnswerLookUp lookUp) {
        if (lookUp.getQuestionId() != null && lookUp.getQuestionId() > 0) {
            predicateList.add(cb.or(cb.equal(root.get(Answer_.question), lookUp.getQuestionId())));
        }
    }

}
