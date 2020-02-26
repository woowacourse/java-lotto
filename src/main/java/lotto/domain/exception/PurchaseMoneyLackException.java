package lotto.domain.exception;

public class PurchaseMoneyLackException extends IllegalArgumentException {
    public PurchaseMoneyLackException(String s) {
        super(s);
    }
}
