package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
import za.co.lindaring.ejb.MessageService;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Answer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Slf4j
@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageAnswerAction")
public class ManageAnswerAction extends BaseAction {

    private static final String DELETE_ANSWER_DIALOG = "deleteAnswerDialog";
    private static final String UPDATE_ANSWER_DIALOG = "updateAnswerDialog";

    private static final String DELETE_ANSWER_MESSAGE = "deleteAnswerMessage";

    private Answer answer;
    private String questionDesc;

    @EJB
    public AnswerService answerService;

    @EJB
    public QuestionService questionService;

    @EJB
    public MessageService messageService;

    public void selectAnswerForDeletion(long answerId) {
        selectAnswer(answerId);
        this.questionDesc = questionService.getQuestion(answer.getQuestionId()).getDesc();
        openDialog(DELETE_ANSWER_DIALOG);
    }

    private void selectAnswer(long questionId) {
        try {
            this.answer = answerService.getAnswer(questionId);
        } catch (Exception e) {
            log.error("Failed to select answer", e);
        }
    }

    public void confirmDeleteAnswer(long answerId) {
        try {
            answerService.deleteAnswer(answerId);
            displayInfo(messageService.getDeleteAnswerSuccessMessage());
        } catch (Exception e) {
            log.error("Failed to delete answer", e);
            displayError( messageService.getDeleteAnswerFailedMessage());
        } finally {
            closeDialog(DELETE_ANSWER_DIALOG);
        }
    }

    public void cancelAnswerQuestion() {
        this.answer = null;
    }

    public void selectAnswerForUpdate(long answerId) {
        selectAnswer(answerId);
        openDialog(UPDATE_ANSWER_DIALOG);
    }

    public void saveAnswer() {
        try {
            answerService.saveAnswer(answer);
            displayInfo(messageService.getUpdateAnswerSuccessMessage());
        } catch (Exception e) {
            log.error("Update question failed", e);
            displayError(messageService.getUpdateAnswerFailedMessage());
        } finally {
            closeDialog(UPDATE_ANSWER_DIALOG);
        }
    }

    public void cancelUpdateAnswer() {
        this.answer = null;
        closeDialog(UPDATE_ANSWER_DIALOG);
    }
}
