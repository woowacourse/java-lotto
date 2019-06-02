package lotto.domain.exception;

public class InvalidLottoPriceException extends IllegalArgumentException {
    public InvalidLottoPriceException(String s) {
        super(s);
    }
}
