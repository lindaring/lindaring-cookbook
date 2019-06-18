package za.co.lindaring.action.answer;

import lombok.Getter;
import lombok.Setter;
import za.co.lindaring.action.base.BaseAction;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "manageAnswerAction")
public class ManageAnswerAction extends BaseAction {

    public void selectAnswerForUpdate(int id) {

    }

    public void selectQuestionForDeletion(int id) {

    }

}
