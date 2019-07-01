package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
import za.co.lindaring.ejb.MessageService;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.entity.Question;
import za.co.lindaring.exception.TechnicalException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;

@Slf4j
@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageAnswerAction")
public class ManageAnswerAction extends BaseAction {

    private static final String DELETE_ANSWER_DIALOG = "deleteAnswerDialog";
    private static final String UPDATE_ANSWER_DIALOG = "updateAnswerDialog";
    private static final String INSERT_ANSWER_DIALOG = "insertAnswerDialog";

    private static final String INSERT_ANSWER_FORM = "insertAnswerForm";

    private static final String DELETE_ANSWER_MESSAGE = "deleteAnswerMessage";
    private static final String INSERT_ANSWER_MESSAGE = "insertAnswerMessage";

    private Answer answer;
    private String questionDesc;

    private String insertAnswerText;
    private Double insertAnswerPoints;
    private Long insertAnswerQuestionId;

    private Boolean answerAdded;

    @EJB
    public AnswerService answerService;

    @EJB
    public QuestionService questionService;

    @EJB
    public MessageService messageService;

    @PostConstruct
    public void init() {
        //Avoid null pointer when loading the view-answers pages
        answer = new Answer();
        answer.setQuestion(new Question());
    }

    public void selectAnswerForDeletion(long answerId) {
        selectAnswer(answerId);
        this.questionDesc = questionService.getQuestion(answer.getQuestion().getId()).getDesc();
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

    public void openInsertAnswerDialog() {
        this.answerAdded = false;
        openDialog(INSERT_ANSWER_DIALOG);
    }

    public void insertAnswer() {
        Question question = questionService.getQuestion(insertAnswerQuestionId);

        Answer newAnswer = Answer.builder()
                .text(insertAnswerText)
                .points(insertAnswerPoints)
                .dateAdded(new Date())
                .question(question)
                .active(1)
                .build();
        try {
            answerService.insertAnswer(newAnswer);
            displayInfo(INSERT_ANSWER_MESSAGE, messageService.getInsertAnswerSuccessMessage(), null);
            resetInsertAnswer();

        } catch (TechnicalException e) {
            log.error(e.getMessage(), e);
            displayError(INSERT_ANSWER_MESSAGE, messageService.getGenericFailedMessage(), null);
        }
    }

    public void cancelInsertAnswer() {
        resetInsertAnswer();
        closeDialog(INSERT_ANSWER_DIALOG);
    }

    public void resetInsertAnswer() {
        this.insertAnswerText = "";
        this.insertAnswerPoints = null;
        this.insertAnswerQuestionId = null;
        this.answerAdded = false;
    }

}
