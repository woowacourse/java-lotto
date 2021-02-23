package lotto.exception.Lotto;

public class LottoNumberCountException extends LottoException {
    private static final String ERROR_MESSAGE = "[ERROR] 당첨 번호는 중복되지 않는 6개의 숫자여야 합니다.";

    public LottoNumberCountException() {
        super(ERROR_MESSAGE);
    }
}