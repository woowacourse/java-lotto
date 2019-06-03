package lotto.domain;

public class LottoBuyingMoney extends Money {
    private static final int LOTTO_PRICE = 1000;

    public LottoBuyingMoney(int lottoBuyingMoney) {
        super(lottoBuyingMoney);
        if (lottoBuyingMoney < LOTTO_PRICE) {
            throw new InvalidLottoBuyingMoneyException("로또 구입 금액은 " + LOTTO_PRICE + "보다 커야합니다.");
        }
        if (lottoBuyingMoney % LOTTO_PRICE != 0) {
            throw new InvalidLottoBuyingMoneyException("로또 구입 금액은 " + LOTTO_PRICE + "의 배수이어야 합니다.");
        }
    }

    public int numOfLottos() {
        return getValue() / LOTTO_PRICE;
    }
}
