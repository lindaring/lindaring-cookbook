package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.entity.Answer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Stateless
@LocalBean
public class AnswerService {

    @PersistenceContext(unitName = "LobolaCalcPU")
    private EntityManager em;

    public Answer getAnswer(long id) {
        return em.find(Answer.class, id);
    }

}
