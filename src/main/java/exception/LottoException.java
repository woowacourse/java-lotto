package exception;

public class LottoException extends IllegalArgumentException {
    private final ErrorMessage errorMessage;

    private LottoException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public static LottoException from(ErrorMessage errorMessage) {
        return new LottoException(errorMessage);
    }
}
