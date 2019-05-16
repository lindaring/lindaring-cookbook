package za.co.lindaring.ejb;

import za.co.lindaring.entity.Question;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class QuestionService {

    @PersistenceContext(unitName = "LobolaCalcPU")
    private EntityManager em;

    public List<Question> getAllQuestions() {
        TypedQuery<Question> result = em.createNamedQuery("Question.findAll", Question.class);
        return result.getResultList();
    }

    public List<Question> getQuestionsLikeDesc(String desc) {
        TypedQuery<Question> result = em.createNamedQuery("Question.findAllLikeDesc", Question.class);
        result.setParameter("description", desc);
        return result.getResultList();
    }

}
