package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 1장의 가격은 1,000원 입니다.");
        }
        this.money = money;
    }

    public int countOfLotto() {
        return money / LOTTO_PRICE;
    }

    public int checkPurchaseLotto(int count) {
        if (countOfLotto() < count) {
            throw new IllegalArgumentException("금액이 부족하여 구입하지 못합니다.");
        }
        return count;
    }

    public int getAutoLottoNumber(int manualLottoNumber) {
        return (money - (manualLottoNumber * LOTTO_PRICE)) / LOTTO_PRICE;
    }
}
