package exception.lotto;

public class LottoNumDuplicatedException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다.";

    public LottoNumDuplicatedException() {
        super(ERROR_MESSAGE);
    }
}
