package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
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

    private static final String ANSWER_QUESTION_DIALOG = "deleteAnswerDialog";

    private Answer answer;

    @EJB
    public AnswerService answerService;

    public void selectAnswerForUpdate(int answerId) {

    }

    public void selectAnswerForDeletion(int answerId) {
        selectAnswer(answerId);
        openDialog(ANSWER_QUESTION_DIALOG);
    }

    private void selectAnswer(long questionId) {
        try {
            this.answer = answerService.getAnswer(questionId);
        } catch (Exception e) {
            log.error("Failed to select answer", e);
        }
    }

    public void confirmDeleteAnswer(Long questionId) {

    }

    public void cancelAnswerQuestion() {

    }

}
