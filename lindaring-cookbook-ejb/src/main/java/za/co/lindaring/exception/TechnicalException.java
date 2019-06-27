package za.co.lindaring.exception;

public class TechnicalException extends Exception {

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
