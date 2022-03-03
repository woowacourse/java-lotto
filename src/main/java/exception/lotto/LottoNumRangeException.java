package exception.lotto;

public class LottoNumRangeException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "로또 번호는 1에서 45사이의 수여야 합니다.";

    public LottoNumRangeException() {
        super(ERROR_MESSAGE);
    }
}
