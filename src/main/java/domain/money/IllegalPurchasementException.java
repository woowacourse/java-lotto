package domain.money;

public class IllegalPurchasementException extends IllegalArgumentException {
    public IllegalPurchasementException() {
        super("구매 금액 입력 오류");
    }
}
