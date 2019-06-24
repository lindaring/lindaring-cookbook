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
    private String deleteQuestionSuccessMessage = "Nice! Question deleted :)";
    private String deleteQuestionFailedMessage = "Oops! Failed to delete question :(";

    private String updateQuestionSuccessMessage = "Nice! Question updated :)";
    private String updateQuestionFailedMessage = "Oops! Failed to update question :(";

    private String deleteAnswerSuccessMessage = "Nice! Answer deleted :)";
    private String deleteAnswerFailedMessage = "Oops! Failed to delete answer :(";

    private String updateAnswerSuccessMessage = "Nice! Answer updated :)";
    private String updateAnswerFailedMessage = "Oops! Failed to update answer :(";

    private String genericeFailedMessage = "Oops! Something went wrong :(";
}
