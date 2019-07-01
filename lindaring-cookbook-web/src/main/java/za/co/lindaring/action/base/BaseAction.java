package za.co.lindaring.action.base;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import za.co.lindaring.types.CookbookDate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

public abstract class BaseAction {

    public String formatDate(Date date) {
        return new CookbookDate(date).formatDate();
    }

    protected void incrementMonthValueInMap(LineChartSeries series, SortedMap<Integer, Long> map) {
        for (int month = 0; month < 12; month++) {
            series.set((month + 1), map.get(month));
        }
    }

    protected void incrementMonthValueInMap(ChartSeries series, SortedMap<Integer, Long> map) throws ParseException {
        int year = (new CookbookDate()).getYear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (int month = 0; month < 12; month++) {
            String strDate = "1-" + (month + 1) + "-" + year;
            Date inputDate = dateFormat.parse(strDate);

            String m = DateFormatUtils.format(inputDate, "MMM");
            series.set(m, map.get(month));
        }
    }

    protected void displayInfo(String message) {
        displayInfo(null, message);
    }

    protected void displayInfo(String id, String message) {
        displayInfo(id, "Nice!  ", message);
    }

    protected void displayInfo(String id, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, header, message));
    }

    protected void displayWarning(String message) {
        displayWarning(null, message);
    }

    protected void displayWarning(String id, String message) {
        displayWarning(id, "Oops!", message);
    }

    protected void displayWarning(String id, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, header, message));
    }

    protected void displayError(String message) {
        displayError(null, message);
    }

    protected void displayError(String id, String message) {
        displayError(id, "Oops!", message);
    }

    protected void displayError(String id, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, header, message));
    }

    protected void displayFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", message));
    }

    protected static void openDialog(String id) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + id + "').show()");
    }

    protected static void closeDialog(String id) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + id + "').hide()");
    }

    protected static void switchTab(String tabViewId, int tabId) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + tabViewId + "').select(" + tabId + ")");
    }

    protected static void disableTab(String tabViewId, int tabId) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + tabViewId + "').disable(" + tabId + ")");
    }

    protected static void enableTab(String tabViewId, int tabId) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + tabViewId + "').enable(" + tabId + ")");
    }

    protected static void clearForm(String formId) {
        PrimeFaces.current().resetInputs(formId);
    }

}
