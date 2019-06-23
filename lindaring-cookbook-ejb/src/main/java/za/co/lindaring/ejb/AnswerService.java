package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Answer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
}
