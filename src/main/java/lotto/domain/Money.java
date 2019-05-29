package lotto.domain;

public class Money {

    private static final int MONEY_PER_LOTTO = 1000;
    private final int money;

    public Money(String money) {
        try {
            this.money = Integer.parseInt(money);
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자가 아닙니다. 금액은 숫자를 입력해야 합니다.");
        }
        checkMinimumMoney();
    }

    private void checkMinimumMoney() {
        if (money < MONEY_PER_LOTTO) {
            throw new IllegalArgumentException("로또의 최소 가격은 1,000원 입니다. 그 이상을 입력해주세요!");
        }
    }

    public int getNumberOfLotto() {
        return money / MONEY_PER_LOTTO;
    }
}
