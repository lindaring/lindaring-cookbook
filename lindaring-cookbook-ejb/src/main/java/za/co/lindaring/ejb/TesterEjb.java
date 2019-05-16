package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.entity.Question;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Startup
@Singleton
public class TesterEjb {

    @PersistenceContext(unitName = "LobolaCalcPU")
    EntityManager em;

    @PostConstruct
    public void testConn() {
        TypedQuery<Question> result = em.createNamedQuery("Question.findAll", Question.class);
        System.out.println("Lobola App test: " + result.getResultList().size());
    }

}
