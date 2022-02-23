package lotto.domain.credit;

public class Credit {

    private final int money;

    public Credit(final int money) {
        validateMoneyIsDivisible(money);
        this.money = money;
    }

    private void validateMoneyIsDivisible(final int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("구입 금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }

    public int getQuotient() {
        return money / 1000;
    }

    public int getMoney() {
        return money;
    }

}
