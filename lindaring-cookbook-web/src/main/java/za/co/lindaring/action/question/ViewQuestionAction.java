package za.co.lindaring.action.question;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;
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
public class ViewQuestionAction {

    private String searchName;
    private Date searchDate;
    private String searchActive;

    private List<Question> questions;

    @EJB
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        questions = questionService.getAllQuestions();
    }

    public void search() {
        Integer active = isNotEmpty(searchActive) ? Integer.parseInt(searchActive) : null;
        questions = questionService.searchQuestion(searchName, searchDate, active);
    }

    public void reset() {
        this.searchName = "";
        this.searchDate = new Date();
        this.searchActive = "";
    }

    public String formatDate(Date date) {
        return new CookbookDate(date).formatDate();
    }

}
