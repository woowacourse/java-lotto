package lotto.domain.exception;

public class OverlapLottoNumberException extends RuntimeException {
    public OverlapLottoNumberException(String content) {
        super(content);
    }
}
