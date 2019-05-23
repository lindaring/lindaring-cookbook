package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Slf4j
@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageQuestionView")
public class ManageQuestionView extends CookbookViewBase {

    private static final String UPDATE_QUESTION_DIALOG = "updateQuestionDialog";
    private static final String DELETE_QUESTION_DIALOG = "deleteQuestionDialog";

    private String resultMessage;
    private Question question;

    @EJB
    private QuestionService questionService;

    public void selectQuestionForUpdate(long questionId) {
        selectQuestion(questionId);
        openDialog(UPDATE_QUESTION_DIALOG);
    }

    public void selectQuestionForDeletion(long questionId) {
        selectQuestion(questionId);
        openDialog(DELETE_QUESTION_DIALOG);
    }

    private void selectQuestion(long questionId) {
        try {
            this.question = questionService.getQuestion(questionId);
        } catch (Exception e) {
            log.error("Failed to select question", e);
        }
    }

    public void confirmDeleteQuestion(Long questionId) {
        if (questionId == null) {
            displayError("Oops! Question not selected:(");
            return;
        }
        try {
            questionService.deleteQuestion(questionId);
            displayInfo("Nice! Question deleted:)");
            closeDialog(DELETE_QUESTION_DIALOG);
        } catch (Exception e) {
            log.error("Failed to delete question", e);
            displayError("Oops! Error deleting question:(");
        }
    }

    public void saveQuestion() {
        try {
            questionService.saveQuestion(question);
            displayInfo("Nice! Saved changes:)");
            closeDialog(UPDATE_QUESTION_DIALOG);
        } catch (Exception e) {
            displayError("Error saving changes");
        }
    }

    public void cancelUpdateQuestion() {
        this.question = null;
        closeDialog(UPDATE_QUESTION_DIALOG);
    }

    public void cancelDeleteQuestion() {
        this.question = null;
        closeDialog(DELETE_QUESTION_DIALOG);
    }

}
