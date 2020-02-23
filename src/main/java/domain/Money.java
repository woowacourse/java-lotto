package domain;

public class Money {
    private static final int MIN_MONEY = 1_000;

    private int money;

    public Money(int money) {
        validateMoneyRange(money);
        this.money = money;
    }

    private void validateMoneyRange(int money) {
        if (money < MIN_MONEY) {
            throw new IllegalArgumentException("금액은 1000원 미만으로 입력할 수 없습니다.");
        }
    }
}
