package za.co.lindaring.ejb;

import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.base.BaseService;
import za.co.lindaring.entity.Answer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Slf4j
@Stateless
@LocalBean
public class ActivityService extends BaseService {

    public Answer getAnswer(long id) {
        return getEntityManager().find(Answer.class, id);
    }

}
