package lotto.exception;

public class LottoException extends IllegalArgumentException {

    public LottoException(LottoExceptionStatus lottoExceptionStatus) {
        super(lottoExceptionStatus.getMessage());
    }

}
