package lotto.domain;

public class Money {
    private static final int MONEY_MIN_CONDITION = 0;

    private final int money;

    public Money(int money) {
        this.money = money;
        validateMoney();
    }

    private void validateMoney() {
        if (money < MONEY_MIN_CONDITION) {
            throw new IllegalArgumentException("금액은 0이상으로 입력해 주세요");
        }
    }

    public int ticketAmount() {
        return money / 1000;
    }
}
