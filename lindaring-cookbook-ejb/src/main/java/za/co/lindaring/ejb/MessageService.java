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
    private String deleteAnswerSuccessMessage = "Nice! Answer deleted :)";
    private String deleteAnswerFailedMessage = "Oops! Failed to delete answer :(";
}
