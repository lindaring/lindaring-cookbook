package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
import za.co.lindaring.ejb.MessageService;
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

    private static final String DELETE_ANSWER_MESSAGE = "deleteAnswerMessage";
    private static final String DELETE_ANSWER_DIALOG = "deleteAnswerDialog";

    private Answer answer;

    @EJB
    public AnswerService answerService;

    @EJB
    public MessageService messageService;

    public void selectAnswerForUpdate(long answerId) {

    }

    public void selectAnswerForDeletion(long answerId) {
        selectAnswer(answerId);
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
            closeDialog(DELETE_ANSWER_DIALOG);
            displayInfo(messageService.getDeleteAnswerSuccessMessage());
        } catch (Exception e) {
            log.error("Failed to delete answer", e);
            displayError(DELETE_ANSWER_MESSAGE, "", messageService.getDeleteAnswerFailedMessage());
        }
    }

    public void cancelAnswerQuestion() {
        this.answer = null;
    }

}
