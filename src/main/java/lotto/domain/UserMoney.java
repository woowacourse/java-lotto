package lotto.domain;

public class UserMoney {
    private int money;
    private int purchase;

    UserMoney(int money) {
        validateMoney(money);
        this.money = money;
        purchase = 0;
    }

    private void validateMoney(int money) {
        if (money <= 0) {
            throw new MoneyException("금액은 양수만 가능합니다.");
        }
    }

    void buy(int cost) {
        if (cost > money) {
            throw new MoneyException("금액이 부족합니다.");
        }
        money -= cost;
        purchase += cost;
    }

    int available() {
        return money;
    }

    @Override
    public String toString() {
        return "잔액 : " + money
                + ", 구입금액 : " + purchase;
    }
}
