package exception;

public interface ExceptionMessage {
    String PREFIX = "[ERROR] ";

    String getMessage(Object... args);
}
