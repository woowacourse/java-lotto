package lotto.domain.exception;

public class InvalidPurchasePriceException extends RuntimeException {
    public InvalidPurchasePriceException() {
        super("구입 금액은 1000원 단위여야 합니다.");
    }
}
