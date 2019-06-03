package lotto.domain;

public class LottoBuyingMoney extends Money {
    private static final int LOTTO_PRICE = 1000;

    public LottoBuyingMoney(int money) {
        super(money);
        if (money < LOTTO_PRICE) {
            throw new InvalidLottoBuyingMoneyException("돈은 " + LOTTO_PRICE + "보다 커야합니다.");
        }
        if (money % LOTTO_PRICE != 0) {
            throw new InvalidLottoBuyingMoneyException("돈은 " + LOTTO_PRICE + "의 배수이어야 합니다.");
        }
    }

    public int numOfLottos() {
        return getValue() / LOTTO_PRICE;
    }
}
