package domain;

public class Money {
    private static final String NUMBER_REGEX = "^[+-]?[0-9]+$";

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

    public int getNumberOfTickets() {
        return this.money / 1000;
    }

    private void validateMoneyUnit(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("천 원 단위로만 구매 가능합니다.");
        }
    }

    public int getMoney() {
        return this.money;
    }
}