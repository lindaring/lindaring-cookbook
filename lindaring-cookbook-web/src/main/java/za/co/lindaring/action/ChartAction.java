package za.co.lindaring.action;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import za.co.lindaring.ejb.QuestionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.SortedMap;

@ManagedBean(name = "chartAction")
public class ChartAction {

    @EJB
    private QuestionService questionService;

    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        SortedMap<Integer, Long> map = questionService.getAllQuestionsGroupByMonthAdded();

        lineModel = new LineChartModel();
        LineChartSeries s = new LineChartSeries();
        s.setLabel("Questions Added");

        for (int month = 0; month < 12; month++) {
            s.set((month + 1), map.get(month));
        }

        lineModel.addSeries(s);
        lineModel.setLegendPosition("e");

        Axis y = lineModel.getAxis(AxisType.Y);
        y.setMin(0);
        //y.setMax(150);
        y.setLabel("Number of Questions");

        Axis x = lineModel.getAxis(AxisType.X);
        x.setMin(1);
        x.setMax(12);
        x.setTickInterval("1");
        x.setLabel("Months");

    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

}