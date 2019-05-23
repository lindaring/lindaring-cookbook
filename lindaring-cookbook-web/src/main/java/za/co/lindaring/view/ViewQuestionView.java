package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;
import za.co.lindaring.util.CookbookUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@RequestScoped
@ManagedBean(name = "viewQuestionView")
public class ViewQuestionView {

    private String searchName;
    private Date searchDate;
    private List<Question> questions;

    @EJB
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        questions = questionService.getAllQuestions();
    }

    public void search() {
        questions = questionService.searchQuestion(searchName, searchDate);
    }

    public String formatDate(Date date) {
        return CookbookUtil.formatDate(date);
    }

}
