package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Question;
import za.co.lindaring.entity.Question_;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.exception.TechnicalException;
import za.co.lindaring.types.CookbookDate;
import za.co.lindaring.types.QuestionLookUp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
@Stateless
@LocalBean
public class QuestionService extends BaseService {

    public Question getQuestion(long id) {
        return getEntityManager().find(Question.class, id);
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

    public List<Question> searchQuestion(QuestionLookUp questionLookUp) throws TechnicalException {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

            CriteriaQuery<Question> criteriaBuilderQuery = criteriaBuilder.createQuery(Question.class);
            Root<Question> root = criteriaBuilderQuery.from(Question.class);
            Predicate predicate = getSearchQuestionPredicate(criteriaBuilder, root, questionLookUp);

            criteriaBuilderQuery.select(root).where(predicate);
            TypedQuery<Question> query = getEntityManager().createQuery(criteriaBuilderQuery);
            return query.getResultList();
        } catch (Exception e) {
            throw new TechnicalException("Search question failed", e);
        }
    }

    private Predicate getSearchQuestionPredicate(CriteriaBuilder criteriaBuilder, Root<Question> questionRoot, QuestionLookUp questionLookUp) {
        final List<Predicate> orPredicates = new ArrayList<>();
        if (questionLookUp != null) {
            if (StringUtils.isNotBlank(questionLookUp.getName())) {
              orPredicates.add(criteriaBuilder.or(criteriaBuilder.like(questionRoot.get(Question_.desc), "%"+questionLookUp.getName()+"%")));
            }
            if (questionLookUp.getStartDate() != null) {
                orPredicates.add(criteriaBuilder.or(criteriaBuilder.greaterThan(questionRoot.get(Question_.dateAdded), questionLookUp.getStartDate())));
                orPredicates.add(criteriaBuilder.or(criteriaBuilder.lessThan(questionRoot.get(Question_.dateAdded), questionLookUp.getEndDate())));
            }
            if (questionLookUp.getActive() != null) {
                orPredicates.add(criteriaBuilder.or(criteriaBuilder.equal(questionRoot.get(Question_.active), questionLookUp.getActive())));
            }
        }
        return criteriaBuilder.and(orPredicates.toArray(new Predicate[orPredicates.size()]));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveQuestion(Question question) {
        getEntityManager().merge(question);
        getEntityManager().flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertQuestion(Question question) {
        question.setId(null);
        getEntityManager().persist(question);
        getEntityManager().flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteQuestion(long id) {
        Question question = getQuestion(id);
        getEntityManager().remove(question);
    }

    private String getSearchQuestionQuery(String desc, Date from, Date to, Integer active) {
        String query;
        if (StringUtils.isNotEmpty(desc) && (from != null || to != null) && active != null) {
            query = "Question.searchByDescAndDateAndActive";
        } else if (StringUtils.isNotEmpty(desc) && (from != null || to != null)) {
            query = "Question.searchByDescAndDate";
        } else if (StringUtils.isNotEmpty(desc) && active != null) {
            query = "Question.searchByDescAndActive";
        } else if ((from != null || to != null) && active != null) {
            query = "Question.searchByDateAndActive";
        } else if (StringUtils.isNotEmpty(desc)) {
            query = "Question.searchByDesc";
        } else if (from != null || to != null) {
            query = "Question.searchByDate";
        } else if (active != null) {
            query = "Question.searchByActive";
        } else {
            query = "Question.findAll";
        }
        return query;
    }

    private TypedQuery<Question> getQuestionTypedQuery(String query, String desc, Date from, Date to, Integer active) throws BusinessException {
        TypedQuery<Question> typedQuery = getEntityManager().createNamedQuery(query, Question.class);
        if (StringUtils.isNotEmpty(desc)) {
            typedQuery.setParameter("description", desc);
        }
        if (active != null) {
            typedQuery.setParameter("active", active);
        }
        getDateTypedQueryParameters(typedQuery, from, to);
        return typedQuery;
    }

}
