package lotto.domain.exception;

public class LottoNotBuyException extends RuntimeException {
    public LottoNotBuyException(String content) {
        super(content);
    }
}
