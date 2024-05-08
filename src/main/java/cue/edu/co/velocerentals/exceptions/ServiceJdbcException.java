package cue.edu.co.velocerentals.exceptions;

public class ServiceJdbcException extends RuntimeException {

    public ServiceJdbcException() {
        super();
    }

    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceJdbcException(String message, String value, Throwable cause) {
        super(String.format("%s: %s", message, value), cause);
    }

}
