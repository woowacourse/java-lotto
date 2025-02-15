package exception;

public class BaseException implements ExceptionMessage {
    private final String message;

    protected BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
