package za.co.lindaring.action.activity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.ActivityService;
import za.co.lindaring.entity.Activity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@RequestScoped
@ManagedBean(name = "activityLogAction")
public class ActivityLogAction {

    private List<Activity> activities = new ArrayList<>();

    @EJB
    public ActivityService activityService;

    @PostConstruct
    public void init() {
        activities = activityService.getAllLogs();
    }

}