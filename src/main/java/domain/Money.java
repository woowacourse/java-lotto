package domain;

public class Money {

    private static final String NONE_NUMERIC_ERROR = "[ERROR] 금액은 숫자만 입력이 가능합니다.";

    private final int money;

    public Money(String money) {
        this.money = isNumeric(money);
    }

    public Money(int money) {
        this.money = money;
    }

    public int money() {
        return this.money;
    }

    private int isNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NONE_NUMERIC_ERROR);
        }
    }
}
