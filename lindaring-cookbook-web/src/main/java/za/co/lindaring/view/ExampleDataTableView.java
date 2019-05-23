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
@ManagedBean(name = "exDataTableView")
public class ExampleDataTableView {

    private String searchName;
    private List<Question> questions;

    @EJB
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        questions = questionService.getAllQuestions();
    }

    public void search() {
        questions = questionService.getQuestionsLikeDesc(searchName);
    }

    public String formatDate(Date date) {
        return CookbookUtil.formatDate(date);
    }

}
