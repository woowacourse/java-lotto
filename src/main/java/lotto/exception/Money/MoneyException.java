package lotto.exception.Money;

public class MoneyException extends RuntimeException {
    private static final String ERROR_MESSAGE = "[ERROR] 구입금액은 1000원 이상이여야 합니다. 입력된 값 : %d";

    public MoneyException(int money) {
        super(String.format(ERROR_MESSAGE, money));
    }
}