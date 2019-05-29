package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Answer;
import za.co.lindaring.entity.Question;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;

@Slf4j
@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageQuestionView")
public class ManageQuestionView extends CookbookViewBase {

    private static final String UPDATE_QUESTION_DIALOG = "updateQuestionDialog";
    private static final String DELETE_QUESTION_DIALOG = "deleteQuestionDialog";
    private static final String INSERT_QUESTION_DIALOG = "insertQuestionDialog";

    private String desc;
    private String resultMessage;
    private String answersString;
    private Question question;

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
        openDialog(INSERT_QUESTION_DIALOG);
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

    public void insertQuestion() {
        try {
            Question newQuestion = Question.builder()
                                            .id(null)
                                            .desc(desc)
                                            .dateAdded(new Date())
                                            .active(1).build();
            questionService.insertQuestion(newQuestion);
            this.desc = "";
            displayInfo("Nice! Saved new question:)");
        } catch (Exception e) {
            displayError("Error saving new question:(");
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

    public void cancelInsertQuestion() {
        this.question = null;
        closeDialog(INSERT_QUESTION_DIALOG);
    }

}
