package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import za.co.lindaring.entity.Question;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Slf4j
@Stateless
@LocalBean
public class QuestionService {

    @PersistenceContext(unitName = "LobolaCalcPU")
    private EntityManager em;

    public Question getQuestion(long id) {
        return em.find(Question.class, id);
    }

    public List<Question> getAllQuestions() {
        TypedQuery<Question> result = em.createNamedQuery("Question.findAll", Question.class);
        return result.getResultList();
    }

    public List<Question> searchQuestion(String desc, Date searchDate) {
        String query = "";
        if (StringUtils.isNotEmpty(desc) && searchDate != null) {
            query = "Question.searchByDescAndDesc";
        } else if (StringUtils.isNotEmpty(desc)) {
            query = "Question.searchByDesc";
        } else if (searchDate != null) {
            query = "Question.searchByDate";
        } else {
            query = "Question.findAll";
        }

//        Query q = em.createQuery(query);
//        em.getEntityManagerFactory().addNamedQuery("selectAuthorOfBook", q);

        TypedQuery<Question> result = em.createNamedQuery(query, Question.class);
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
        em.merge(question);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertQuestion(Question question) {
        question.setId(null);
        em.persist(question);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteQuestion(long id) {
        Question question = getQuestion(id);
        em.remove(question);
    }

}
