package domain;

public class Money {
    private static final String NUMBER_REGEX = "^[0-9]+$";
    private static final int MONEY_UNIT = 1000;

    private final int money;

    public Money(String moneyInput) {
        int money = parseInt(moneyInput);
        validateMoneyUnit(money);
        this.money = money;
    }

    private static int parseInt(String input) {
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new NumberFormatException("0원 이상 숫자를 입력해주세요.");
        }
    }

    private void validateMoneyUnit(int money) {
        if (money % MONEY_UNIT != 0) {
            throw new IllegalArgumentException(String.format("%d원 단위로만 구매 가능합니다.", MONEY_UNIT));
        }
    }

    public int getValue() {
        return this.money;
    }
}