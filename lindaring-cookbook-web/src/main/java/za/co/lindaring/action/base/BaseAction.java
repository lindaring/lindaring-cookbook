package za.co.lindaring.action.base;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class BaseAction {

    protected void displayInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
        displayInfo(null, message);
    }

    protected void displayInfo(String id, String message) {
        displayInfo(id, "Success", message);
    }

    protected void displayInfo(String id, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, header, message));
    }

    protected void displayWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", message));
    }

    protected void displayError(String message) {
        displayError(null, message);
    }

    protected void displayError(String id, String message) {
        displayError(id, "Error!", message);
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

}
