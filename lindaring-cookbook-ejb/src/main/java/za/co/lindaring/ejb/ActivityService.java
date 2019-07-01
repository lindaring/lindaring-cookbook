package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Activity;
import za.co.lindaring.entity.Answer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Stateless
@LocalBean
public class ActivityService extends BaseService {

    public Answer getActivity(long id) {
        return getEntityManager().find(Answer.class, id);
    }

    public List<Activity> getAllActivities() {
        TypedQuery<Activity> result = getEntityManager().createNamedQuery("Activity.findAll", Activity.class);
        return result.getResultList();
    }

}
