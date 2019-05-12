package za.co.lindaring.service;

import lombok.Getter;
import za.co.lindaring.model.Question;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
@ManagedBean(name = "exDataTableService")
public class ExampleDataTableService {

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question(1, "This is question one.", new Date(), 1));
        questionList.add(new Question(2, "This is question two.", new Date(), 1));
        questionList.add(new Question(3, "This is question three.", new Date(), 0));
        questionList.add(new Question(4, "This is question four.", new Date(), 1));
        questionList.add(new Question(5, "This is question five.", new Date(), 0));
        return questionList;
    }
}
