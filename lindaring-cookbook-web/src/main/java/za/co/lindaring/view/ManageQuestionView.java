package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageQuestionView")
public class ManageQuestionView {

    private String resultMessage;
    private Question question;

    @EJB
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();

        String profileId = paramMap.get("questionId");
        if (profileId != null) {
            long id = Long.parseLong(paramMap.get("questionId"));
            question = questionService.getQuestion(id);
        }
    }

    public void saveQuestion() {
        try {
            questionService.saveQuestion(question);
            setInfoMessage("Saved changes:)");
        } catch (Exception e) {
            setErrorMessage("Error saving changes");
        }
    }


    private void setInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }

    private void setErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }

}
