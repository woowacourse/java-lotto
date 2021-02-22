package lotto.exception.Lotto;

public class LottoNumberScopeException extends LottoException {
    public static final String ERROR_MESSAGE = "당첨 번호는 1 ~ 45 사이의 수여야만 합니다.";

    public LottoNumberScopeException() {
        super(ERROR_MESSAGE);
    }
}