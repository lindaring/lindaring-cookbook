package za.co.lindaring.action;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import za.co.lindaring.action.base.BaseAction;
import za.co.lindaring.ejb.AnswerService;
import za.co.lindaring.ejb.QuestionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.SortedMap;

@Setter
@Getter
@ViewScoped
@ManagedBean(name = "chartAction")
public class ChartAction extends BaseAction {

    @EJB
    private QuestionService questionService;

    @EJB
    private AnswerService answerService;

    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        lineModel = new LineChartModel();
        addQuestionsAddedPerMonthLineGraph();
        addAnswersAddedPerMonthLineGraph();
        lineModel.setLegendPosition("e");

        Axis y = lineModel.getAxis(AxisType.Y);
        y.setMin(0);
        //y.setMax(150);
        y.setLabel("Number of Questions/Answers");

        Axis x = lineModel.getAxis(AxisType.X);
        x.setMin(1);
        x.setMax(12);
        x.setTickInterval("1");
        x.setLabel("Months");
    }

    private void addQuestionsAddedPerMonthLineGraph() {
        addLineGraph("Questions Added", questionService.getAllQuestionsGroupByMonthAdded());
    }

    private void addAnswersAddedPerMonthLineGraph() {
        addLineGraph("Answers Added", answerService.getAllAnswersGroupByMonthAdded());
    }

    private void addLineGraph(String label, SortedMap<Integer, Long> map) {
        LineChartSeries answerSeries = new LineChartSeries();
        answerSeries.setLabel(label);
        incrementMonthValueInMap(answerSeries, map);
        lineModel.addSeries(answerSeries);
    }

}