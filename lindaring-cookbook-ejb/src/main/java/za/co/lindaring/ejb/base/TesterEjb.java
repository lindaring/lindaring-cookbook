package za.co.lindaring.ejb.base;

import za.co.lindaring.entity.Question;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

//@Startup
@Singleton
public class TesterEjb {

    @PersistenceContext(unitName = "LobolaCalcPU")
    EntityManager em;

    @PostConstruct
    public void testConn() {
//        TypedQuery<Answer> result = em.createNamedQuery("Answer.findAll", Answer.class);
//        List<Answer> list = result.getResultList();
        TypedQuery<Question> result = em.createNamedQuery("Question.findAll", Question.class);
        List<Question> list = result.getResultList();
        System.out.println("Lobola App test: " + list.size());
    }

}
