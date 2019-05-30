package lotto.domain.purchase;

public class InvalidPurchaseAmount extends RuntimeException {
    public InvalidPurchaseAmount(String msg) {
        super(msg);
    }
}
