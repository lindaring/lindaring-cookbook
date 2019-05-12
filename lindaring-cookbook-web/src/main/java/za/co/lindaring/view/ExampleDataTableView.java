package za.co.lindaring.view;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.DateFormatUtils;
import za.co.lindaring.entity.Question;
import za.co.lindaring.service.ExampleDataTableService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "exDataTableView")
public class ExampleDataTableView {

    private List<Question> questions;

    @ManagedProperty("#{exDataTableService}")
    private ExampleDataTableService dataTableService;

    @PostConstruct
    public void init() {
        questions = dataTableService.getAllQuestions();
    }

    public String formatDate(Date date) {
        return DateFormatUtils.format(date, "dd MMM yyyy");
    }

}
