package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.entity.Question;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public List<Question> getQuestionsLikeDesc(String desc) {
        TypedQuery<Question> result = em.createNamedQuery("Question.findAllLikeDesc", Question.class);
        result.setParameter("description", desc);
        return result.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveQuestion(Question question) {
        em.merge(question);
        em.flush();
    }

    public void deleteQuestion() {

    }

}
