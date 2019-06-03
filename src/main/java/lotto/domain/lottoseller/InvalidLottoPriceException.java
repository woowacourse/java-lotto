package lotto.domain.lottoseller;

public class InvalidLottoPriceException extends IllegalArgumentException {
    public InvalidLottoPriceException(String s) {
        super(s);
    }
}
