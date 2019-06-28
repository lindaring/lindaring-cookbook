package za.co.lindaring.ejb;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Slf4j
@Getter
@Startup
@Singleton
@LocalBean
public class MessageService {
    private String deleteQuestionSuccessMessage = "Question deleted :)";
    private String deleteQuestionFailedMessage = "Failed to delete question :(";

    private String updateQuestionSuccessMessage = "Question updated :)";
    private String updateQuestionFailedMessage = "Failed to update question :(";

    private String deleteAnswerSuccessMessage = "Answer deleted :)";
    private String deleteAnswerFailedMessage = "Failed to delete answer :(";

    private String updateAnswerSuccessMessage = "Answer updated :)";
    private String updateAnswerFailedMessage = "Failed to update answer :(";

    private String genericFailedMessage = "Something went wrong :(";

    private String startDateNotEnteredMessage = "Enter the start date.";
    private String endDateNotEnteredMessage = "Enter the end date.";
}
