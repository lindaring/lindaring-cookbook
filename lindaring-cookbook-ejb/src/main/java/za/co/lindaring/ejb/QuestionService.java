package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Question;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

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

    public List<Question> searchQuestion(String desc, Date searchDate) {
        String query;
        if (StringUtils.isNotEmpty(desc) && searchDate != null) {
            query = "Question.searchByDescAndDesc";
        } else if (StringUtils.isNotEmpty(desc)) {
            query = "Question.searchByDesc";
        } else if (searchDate != null) {
            query = "Question.searchByDate";
        } else {
            query = "Question.findAll";
        }

        TypedQuery<Question> result = getEntityManager().createNamedQuery(query, Question.class);
        if (StringUtils.isNotEmpty(desc)) {
            result.setParameter("description", desc);
        }
        if (searchDate != null) {
            result.setParameter("sDate", searchDate);
        }
        return result.getResultList();
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

}
