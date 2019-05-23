package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;
import za.co.lindaring.util.CookbookUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

        String id = paramMap.get("questionId");
        if (StringUtils.isNotEmpty(id)) {
            question = questionService.getQuestion(Long.parseLong(id));
        }
    }

    public void saveQuestion() {
        try {
            questionService.saveQuestion(question);
            CookbookUtil.displayInfo("Saved changes:)");
        } catch (Exception e) {
            CookbookUtil.displayError("Error saving changes");
        }
    }

}
