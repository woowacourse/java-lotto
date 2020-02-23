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

    public int getLottoTicketCount() {
        return this.money / MIN_MONEY;
    }

    public int getMoney() {
        return this.money;
    }

    @Override
    public String toString() {
        return Integer.toString(this.money / MIN_MONEY);
    }
}
