package za.co.lindaring.view;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

abstract class CookbookViewBase {

    void displayInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
        displayInfo(null, message);
    }

    void displayInfo(String id, String message) {
        displayInfo(id, "Success", message);
    }

    void displayInfo(String id, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, header, message));
    }

    void displayWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", message));
    }

    void displayError(String message) {
        displayError(null, message);
    }

    void displayError(String id, String message) {
        displayError(id, "Error!", message);
    }

    void displayError(String id, String header, String message) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, header, message));
    }

    void displayFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", message));
    }

    static void openDialog(String id) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + id + "').show()");
    }

    static void closeDialog(String id) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + id + "').hide()");
    }

    static void switchTab(String tabViewId, int tabId) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + tabViewId + "').select(" + tabId + ")");
    }

    static void disableTab(String tabViewId, int tabId) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + tabViewId + "').disable(" + tabId + ")");
    }

    static void enableTab(String tabViewId, int tabId) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + tabViewId + "').enable(" + tabId + ")");
    }

}
