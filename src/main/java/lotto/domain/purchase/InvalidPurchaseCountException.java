package lotto.domain.purchase;

public class InvalidPurchaseCountException extends RuntimeException {
    public InvalidPurchaseCountException(String msg) {
        super(msg);
    }
}
