package exception;

public class AppException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";

    public AppException(final String message) {
        super(PREFIX + message);
    }
}
