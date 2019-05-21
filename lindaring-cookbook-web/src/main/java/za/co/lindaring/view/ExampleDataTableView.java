package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.primefaces.PrimeFaces;
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

    private Question selectedQuestion;

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

    public void selectQuestion(long questionId) {
        for (Question question : questions) {
            if (question.getId() == questionId) {
                this.selectedQuestion = question;
            }
        }
        CookbookUtil.openDialog("deleteQuestionDialog");
    }

    public void confirmDeleteQuestion() {
        CookbookUtil.displayInfo("Question deleted.");
    }

    public void cancelDeleteQuestion() {
        this.selectedQuestion = null;
        CookbookUtil.closeDialog("deleteQuestionDialog");
    }

}
