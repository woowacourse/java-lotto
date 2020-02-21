package lotto.Exception;

public class IllegalPurchaseAmount extends RuntimeException {
    public IllegalPurchaseAmount(String message) {
        super(message);
    }
}
