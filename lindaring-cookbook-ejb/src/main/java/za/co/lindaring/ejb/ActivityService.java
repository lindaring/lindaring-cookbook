package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Activity;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.exception.TechnicalException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Stateless
@LocalBean
public class ActivityService extends BaseService {

    public Answer getLog(long id) {
        return getEntityManager().find(Answer.class, id);
    }

    public List<Activity> getAllLogs() {
        TypedQuery<Activity> result = getEntityManager().createNamedQuery("Activity.findAll", Activity.class);
        return result.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertLog(Activity activity) throws TechnicalException {
        try {
            getEntityManager().persist(activity);
            getEntityManager().flush();

        } catch (Exception e) {
            throw new TechnicalException("Insert activity failed", e);
        }
    }

}
