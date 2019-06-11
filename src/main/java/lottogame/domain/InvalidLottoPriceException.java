package lottogame.domain;

public class InvalidLottoPriceException extends RuntimeException {
    public InvalidLottoPriceException(String message) {
        super(message);
    }
}
