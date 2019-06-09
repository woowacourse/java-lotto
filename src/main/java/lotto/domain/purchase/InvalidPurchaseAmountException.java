package lotto.domain.purchase;

public class InvalidPurchaseAmountException extends RuntimeException {
    public InvalidPurchaseAmountException(String msg) {
        super(msg);
    }
}
