package za.co.lindaring.view;

import org.apache.commons.lang3.time.DateFormatUtils;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.List;

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

    public String formatDate(Date date) {
        return DateFormatUtils.format(date, "dd MMM yyyy");
    }

    public void search() {
        questions = questionService.getQuestionsLikeDesc(searchName);
        System.out.println(questions.size());
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
