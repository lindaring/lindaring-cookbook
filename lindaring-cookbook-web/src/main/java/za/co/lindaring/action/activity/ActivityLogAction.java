package za.co.lindaring.action.activity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.action.user.UserAction;
import za.co.lindaring.ejb.ActivityService;
import za.co.lindaring.entity.Activity;
import za.co.lindaring.exception.TechnicalException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Setter
@Getter
@RequestScoped
@ManagedBean(name = "activityLogAction")
public class ActivityLogAction {

    private List<Activity> activities = new ArrayList<>();

    @EJB
    private ActivityService activityService;

    @Inject
    private UserAction userAction;

    @PostConstruct
    public void init() {
        activities = activityService.getAllLogs();
    }

    public void logResult(String logMessage) {
        Activity activity = Activity.builder().text(logMessage).dateAdded(new Date())
                .user(userAction.getUserIpAddress()).build();
        try {
            activityService.insertLog(activity);

        } catch (TechnicalException e) {
            log.error(String.format("Failed to log result %s.", activity));
            //Don't fail as this is just a log
        }
    }

}