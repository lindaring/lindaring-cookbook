package za.co.lindaring.action;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Setter
@Getter
@SessionScoped
@ManagedBean(name = "tableViewAction")
public class TableViewAction {

    private String rowsPerPage;
    private String selectedRowsPerPage;

    @PostConstruct
    public void init() {
        rowsPerPage = "10,15,25,35,50";
        selectedRowsPerPage = "15";
    }

}
