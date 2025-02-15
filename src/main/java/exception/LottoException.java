package exception;

public class LottoException extends IllegalArgumentException {
    private final ExceptionMessage exceptionMessage;

    private LottoException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public static LottoException from(ExceptionMessage exceptionMessage) {
        return new LottoException(exceptionMessage);
    }
}
