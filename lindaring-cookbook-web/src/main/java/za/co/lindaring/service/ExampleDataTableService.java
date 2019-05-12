package za.co.lindaring.service;

import za.co.lindaring.ejb.QuestionService;
import za.co.lindaring.entity.Question;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ApplicationScoped
@ManagedBean(name = "exDataTableService")
public class ExampleDataTableService {

    @EJB
    private QuestionService questionService;

    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

}
