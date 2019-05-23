package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;
import za.co.lindaring.util.CookbookUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageQuestionView")
public class ManageQuestionView {

    private static final String UPDATE_QUESTION_DIALOG = "updateQuestionDialog";

    private String resultMessage;
    private Question question;

    @EJB
    private QuestionService questionService;

    public void selectQuestionForUpdate(long questionId) {
        question = questionService.getQuestion(questionId);
        CookbookUtil.openDialog(UPDATE_QUESTION_DIALOG);
    }

    public void cancelUpdateQuestion() {
        this.question = null;
        CookbookUtil.closeDialog(UPDATE_QUESTION_DIALOG);
    }

    public void saveQuestion() {
        try {
            questionService.saveQuestion(question);
            CookbookUtil.displayInfo("Saved changes:)");
        } catch (Exception e) {
            CookbookUtil.displayError("Error saving changes");
        }
    }

}
