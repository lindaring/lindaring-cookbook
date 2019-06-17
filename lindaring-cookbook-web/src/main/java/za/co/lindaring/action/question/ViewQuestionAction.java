package za.co.lindaring.action.question;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;
import za.co.lindaring.exception.BusinessException;
import za.co.lindaring.types.CookbookDate;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Setter
@Getter
@RequestScoped
@ManagedBean(name = "ViewQuestionAction")
public class ViewQuestionAction extends BaseAction {

    private String searchName;
    private Date searchFromDate;
    private Date searchToDate;
    private String searchActive;

    private List<Question> questions;

    @EJB
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        questions = questionService.getAllQuestions();
    }

    public void search() {
        try {
            Integer active = isNotEmpty(searchActive) ? Integer.parseInt(searchActive) : null;
            questions = questionService.searchQuestion(searchName, searchFromDate, searchToDate, active);
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

    public String formatDate(Date date) {
        return new CookbookDate(date).formatDate();
    }

}
