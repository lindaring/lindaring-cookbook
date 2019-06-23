package za.co.lindaring.action.question;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.entity.Question;
import za.co.lindaring.action.base.BaseAction;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;

@Slf4j
@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageQuestionAction")
public class ManageQuestionAction extends BaseAction {

    private static final String UPDATE_QUESTION_DIALOG = "updateQuestionDialog";
    private static final String DELETE_QUESTION_DIALOG = "deleteQuestionDialog";
    private static final String INSERT_QUESTION_DIALOG = "insertQuestionDialog";
    private static final String TAB_QUESTION_MESSAGE_VIEW = "tabViewQuestionMessage";
    private static final String TAB_ANSWER_MESSAGE_VIEW = "tabViewAnswerMessage";

    private String desc;
    private String resultMessage;
    private String answersString;

    private Question question;
    private List<Answer> answerList;

    private String insertQuestionText;
    private String insertAnswer1Text;
    private String insertAnswer2Text;
    private String insertAnswer3Text;
    private String insertAnswer4Text;

    private boolean questionAdded;

    @EJB
    private QuestionService questionService;

    public void selectQuestionForUpdate(long questionId) {
        selectQuestion(questionId);
        StringBuilder answers = new StringBuilder();
        for (Answer a: question.getAnswers()) {
            answers.append(a.getText()).append("\n");
        }
        answersString = answers.toString();
        openDialog(UPDATE_QUESTION_DIALOG);
    }

    public void selectQuestionForDeletion(long questionId) {
        selectQuestion(questionId);
        openDialog(DELETE_QUESTION_DIALOG);
    }

    public void openInsertQuestionDialog() {
        this.questionAdded = false;
        enableTab("insQuestionTabView", 0);
        switchTab("insQuestionTabView", 0);
        disableTab("insQuestionTabView", 1);
        openDialog(INSERT_QUESTION_DIALOG);
    }

    private void selectQuestion(long questionId) {
        try {
            this.question = questionService.getQuestion(questionId);
        } catch (Exception e) {
            log.error("Failed to select question", e);
        }
    }

    public void confirmDeleteQuestion(long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            closeDialog(DELETE_QUESTION_DIALOG);
            displayInfo("Nice! Question deleted:)");
        } catch (Exception e) {
            log.error("Failed to delete question", e);
            displayError("Oops! Error deleting question:(");
        }
    }

    public void saveQuestion() {
        try {
            questionService.saveQuestion(question);
            closeDialog(UPDATE_QUESTION_DIALOG);
            displayInfo("Nice! Saved changes:)");
        } catch (Exception e) {
            displayError("Error saving changes:(");
        }
    }

    public void insertSwitchTab() {
        this.questionAdded = false;
        enableTab("insQuestionTabView", 0);
        switchTab("insQuestionTabView", 0);
        disableTab("insQuestionTabView", 1);
    }

    public void insertQuestionNext() {
        if (isBlank(insertQuestionText)) {
            displayError(TAB_QUESTION_MESSAGE_VIEW, "Enter the question.", null);
        } else {
            disableTab("insQuestionTabView", 0);
            enableTab("insQuestionTabView", 1);
            switchTab("insQuestionTabView", 1);
        }
    }

    public void insertQuestionComplete() {
        if (isBlank(insertAnswer1Text) || isBlank(insertAnswer2Text) || isBlank(insertAnswer3Text) || isBlank(insertAnswer4Text)) {
            displayError(TAB_ANSWER_MESSAGE_VIEW, "Enter all 4 answers.", null);
            return;
        } else if (isBlank(insertQuestionText)) {
            displayError(TAB_ANSWER_MESSAGE_VIEW, "Question not entered.", null);
            return;
        }

        Date now = new Date();

        Question newQuestion = new Question();
        newQuestion.setDesc(insertQuestionText);
        newQuestion.setActive(1);
        newQuestion.setDateAdded(now);
        newQuestion.getAnswers().add(buildAnswer(newQuestion, insertAnswer1Text, now));
        newQuestion.getAnswers().add(buildAnswer(newQuestion, insertAnswer2Text, now));
        newQuestion.getAnswers().add(buildAnswer(newQuestion, insertAnswer3Text, now));
        newQuestion.getAnswers().add(buildAnswer(newQuestion, insertAnswer4Text, now));

        try {
            questionService.insertQuestion(newQuestion);
            resetInsertQuestion();
            displayInfo(TAB_ANSWER_MESSAGE_VIEW, "Nice! Saved new question:)", null);
        } catch (Exception e) {
            displayInfo(TAB_ANSWER_MESSAGE_VIEW, "Error saving new question:(", null);
        }
    }

    private Answer buildAnswer(Question question, String text, Date now) {
        return Answer.builder()
                .text(text)
                .points(100.0)
                .active(1)
                .dateAdded(now)
                .questionId(question.getId())
                .build();
    }

    public void resetInsertQuestion() {
        this.insertQuestionText = "";
        this.insertAnswer1Text = "";
        this.insertAnswer2Text = "";
        this.insertAnswer3Text = "";
        this.insertAnswer4Text = "";
        this.questionAdded = true;
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
