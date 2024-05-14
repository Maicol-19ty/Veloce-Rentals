package cue.edu.co.velocerentals.exceptions;

// Custom exception class for JDBC service-related errors.
public class ServiceJdbcException extends RuntimeException {

    // Default constructor.
    public ServiceJdbcException() {
        super();
    }

    // Constructor with a message parameter.
    public ServiceJdbcException(String message) {
        super(message);
    }

    // Constructor with message and cause parameters.
    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with message, value, and cause parameters.
    public ServiceJdbcException(String message, String value, Throwable cause) {
        super(String.format("%s: %s", message, value), cause);
    }

}
