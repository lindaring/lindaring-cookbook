package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.entity.Question;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@Getter
@Setter
@RequestScoped
@ManagedBean(name = "manageQuestionView")
public class ManageQuestionView {

    private Question question;
    private String resultMessage;

    @PostConstruct
    public void init() {
       if (question == null) {
           question = new Question();
       }
    }

    public void addQuestion() {
        resultMessage = "working"; //TODO
    }

}
