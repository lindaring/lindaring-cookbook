package za.co.lindaring.view;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

abstract class CookbookViewBase {

    void displayInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
    }

    void displayWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", message));
    }

    void displayError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", message));
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

}
