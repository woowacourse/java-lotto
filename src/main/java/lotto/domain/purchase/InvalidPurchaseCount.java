package lotto.domain.purchase;

public class InvalidPurchaseCount extends RuntimeException {
    public InvalidPurchaseCount(String msg) {
        super(msg);
    }
}
