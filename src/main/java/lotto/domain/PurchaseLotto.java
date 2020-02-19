package lotto.domain;

public class PurchaseLotto {
    private static final int LOTTO_PRICE = 1000;

    public static int purchase(int money) {
        purchaseException(money);
        return money / LOTTO_PRICE;
    }

    static void purchaseException(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("돈이 부족해 로또를 살 수 없습니다.");
        }
    }
}
