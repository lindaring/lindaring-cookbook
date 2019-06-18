package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.types.CookbookDate;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@RequestScoped
@ManagedBean(name = "ViewAnswerAction")
public class ViewAnswerAction extends BaseAction {

    private List<Answer> answers = new ArrayList<>();

    @EJB
    public AnswerService answerService;

    @PostConstruct
    public void init() {
        answers = answerService.getAllAnswers();
    }

    public String formatDate(Date date) {
        return new CookbookDate(date).formatDate();
    }

}
