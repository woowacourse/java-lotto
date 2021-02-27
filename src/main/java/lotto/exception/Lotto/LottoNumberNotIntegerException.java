package lotto.exception.Lotto;

public class LottoNumberNotIntegerException extends LottoException {
    private static final String ERROR_MESSAGE = "[ERROR] 로또 번호는 숫자를 입력해주세요.";

    public LottoNumberNotIntegerException() {
        super(ERROR_MESSAGE);
    }
}
