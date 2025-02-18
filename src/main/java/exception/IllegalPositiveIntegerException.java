package exception;

public class IllegalPositiveIntegerException extends IllegalArgumentException {
    private static final String message = "양의 정수여야 합니다.";

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
