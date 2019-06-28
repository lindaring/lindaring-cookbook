package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.types.AnswerLookUp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
@Stateless
@LocalBean
public class AnswerService extends BaseService {

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
    public void saveAnswer(Answer answer) {
        getEntityManager().merge(answer);
        getEntityManager().flush();
    }

    public List<Answer> searchAnswer(AnswerLookUp answerLookUp) throws BusinessException {
        String query = getSearchAnswerQuery(answerLookUp.getText(), answerLookUp.getStartDate(),
                answerLookUp.getEndDate(), answerLookUp.getActive());
        TypedQuery<Answer> result = getAnswerTypedQuery(query, answerLookUp.getText(), answerLookUp.getStartDate(),
                answerLookUp.getEndDate(), answerLookUp.getActive());
        return result.getResultList();
    }

    private String getSearchAnswerQuery(String text, Date from, Date to, Integer active) {
        String query;
        if (StringUtils.isNotEmpty(text) && (from != null || to != null) && active != null) {
            query = "Answer.searchByTextAndDateAndActive";
        } else if (StringUtils.isNotEmpty(text) && (from != null || to != null)) {
            query = "Answer.searchByTextAndDate";
        } else if (StringUtils.isNotEmpty(text) && active != null) {
            query = "Answer.searchByTextAndActive";
        } else if (StringUtils.isNotEmpty(text)) {
            query = "Answer.searchByText";
        } else if ((from != null || to != null) && active != null) {
            query = "Answer.searchByDateAndActive";
        } else if (from != null || to != null) {
            query = "Answer.searchByDate";
        } else if (active != null) {
            query = "Answer.searchByActive";
        } else {
            query = "Answer.findAll";
        }
        return query;
    }

    private TypedQuery<Answer> getAnswerTypedQuery(String query, String text, Date from, Date to, Integer active) throws BusinessException {
        TypedQuery<Answer> typedQuery = getEntityManager().createNamedQuery(query, Answer.class);
        if (StringUtils.isNotEmpty(text)) {
            typedQuery.setParameter("text", text);
        }
        if (active != null) {
            typedQuery.setParameter("active", active);
        }
        getDateTypedQueryParameters(typedQuery, from, to);
        return typedQuery;
    }

}
