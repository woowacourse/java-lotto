package lotto.domain;

public class LottoBuyingMoney {
    private static final int LOTTO_PRICE = 1000;
    private final int money;

    public LottoBuyingMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new InvalidMoneyException("돈은 " + LOTTO_PRICE + "보다 커야합니다.");
        }
        if (money % LOTTO_PRICE != 0) {
            throw new InvalidMoneyException("돈은 " + LOTTO_PRICE + "의 배수이어야 합니다.");
        }
        this.money = money;
    }

    public LottoBuyingMoney add(LottoBuyingMoney another) {
        return new LottoBuyingMoney(money + another.money);
    }

    public int getValue() {
        return money;
    }

    public int numOfLottos() {
        return money / LOTTO_PRICE;
    }
}
