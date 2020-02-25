package lotto.exception;

public class MoneyException extends IllegalArgumentException {
    public MoneyException(String message) {
        super(message);
    }

    public MoneyException() {
        this("구매금액은 1000원 이상이어야 합니다.");
    }
}
