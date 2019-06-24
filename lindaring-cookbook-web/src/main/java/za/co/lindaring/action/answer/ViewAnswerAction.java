package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.types.CookbookDate;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Setter
@Getter
@RequestScoped
@ManagedBean(name = "ViewAnswerAction")
public class ViewAnswerAction extends BaseAction {

    private String searchName;
    private Date searchFromDate;
    private Date searchToDate;
    private String searchActive;

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

    public void search() {
        try {
            Integer active = isNotEmpty(searchActive) ? Integer.parseInt(searchActive) : null;
            answers = answerService.searchAnswer(searchName, searchFromDate, searchToDate, active);
        } catch (BusinessException e) {
            displayError(e.getMessage());
        }
    }

    public void reset() {
        this.searchName = "";
        this.searchFromDate = null;
        this.searchToDate = null;
        this.searchActive = "";
    }

}
