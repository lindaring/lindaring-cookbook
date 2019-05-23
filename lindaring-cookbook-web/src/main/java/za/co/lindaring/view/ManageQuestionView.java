package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;
import za.co.lindaring.util.CookbookUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Slf4j
@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageQuestionView")
public class ManageQuestionView {

    private static final String UPDATE_QUESTION_DIALOG = "updateQuestionDialog";
    private static final String DELETE_QUESTION_DIALOG = "deleteQuestionDialog";

    private String resultMessage;
    private Question question;

    @EJB
    private QuestionService questionService;

    public void selectQuestionForUpdate(long questionId) {
        question = questionService.getQuestion(questionId);
        CookbookUtil.openDialog(UPDATE_QUESTION_DIALOG);
    }

    public void selectQuestionForDeletion(long questionId) {
        try {
            this.question = questionService.getQuestion(questionId);
            CookbookUtil.openDialog(DELETE_QUESTION_DIALOG);
        } catch (Exception e) {
            log.error("Failed to select question", e);
        }
    }

    public void confirmDeleteQuestion(Long questionId) {
        if (questionId == null) {
            CookbookUtil.displayError("Oops! Question not selected:(");
            return;
        }
        try {
            questionService.deleteQuestion(questionId);
            CookbookUtil.displayInfo("Nice! Question deleted:)");
            CookbookUtil.closeDialog(DELETE_QUESTION_DIALOG);
        } catch (Exception e) {
            log.error("Failed to delete question", e);
            CookbookUtil.displayError("Oops! Error deleting question:(");
        }
    }

    public void saveQuestion() {
        try {
            questionService.saveQuestion(question);
            CookbookUtil.displayInfo("Saved changes:)");
        } catch (Exception e) {
            CookbookUtil.displayError("Error saving changes");
        }
    }

    public void cancelUpdateQuestion() {
        this.question = null;
        CookbookUtil.closeDialog(UPDATE_QUESTION_DIALOG);
    }

    public void cancelDeleteQuestion() {
        this.question = null;
        CookbookUtil.closeDialog(DELETE_QUESTION_DIALOG);
    }

}
