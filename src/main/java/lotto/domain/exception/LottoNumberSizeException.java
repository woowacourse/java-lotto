package lotto.domain.exception;

public class LottoNumberSizeException extends RuntimeException {
    public LottoNumberSizeException(String content) {
        super(content);
    }
}
