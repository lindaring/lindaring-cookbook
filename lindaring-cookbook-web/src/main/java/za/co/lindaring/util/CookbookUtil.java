package za.co.lindaring.util;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class CookbookUtil {

    private CookbookUtil() {
    }

    public static void openDialog(String id) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + id + "').show()");
    }

    public static void closeDialog(String id) {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('" + id + "').hide()");
    }

    public static void displayInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
    }

    public static void displayWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", message));
    }

    public static void displayError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", message));
    }

    public static void displayFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", message));
    }

}
