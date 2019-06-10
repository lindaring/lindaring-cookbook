package za.co.lindaring.action.charts;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import za.co.lindaring.action.base.BaseAction;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Setter
@Getter
@ViewScoped
@ManagedBean(name = "barGraphAction")
public class BarGraphAction extends BaseAction {

    private BarChartModel barModel;

    @PostConstruct
    private void createBarModel() {
        barModel = new BarChartModel();

        addVisitorsPerMonthBarGraph();

        barModel.setTitle("Visitors Per Month");
        barModel.setLegendPosition("ne");

        initAxisX();
        initAxisY();
    }

    private void initAxisX() {
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Months");
    }

    private void initAxisY() {
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Users");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private void addVisitorsPerMonthBarGraph() {
        ChartSeries months = new ChartSeries();
        months.setLabel("Months");

        months.set("JAN", 120);
        months.set("FEB", 100);
        months.set("MAR", 44);
        months.set("APR", 150);
        months.set("MAY", 25);
        months.set("JUN", 120);
        months.set("JUL", 100);
        months.set("AUG", 44);
        months.set("SEP", 150);
        months.set("OCT", 25);
        months.set("NOV", 120);
        months.set("DEV", 100);

        barModel.addSeries(months);
    }

}
