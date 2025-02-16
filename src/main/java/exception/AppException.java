package exception;

public class AppException extends IllegalArgumentException {
    public AppException(final ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
