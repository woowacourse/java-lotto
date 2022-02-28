package domain;

public class Money {

    private int money;

    public Money(int money) {
        this.money = money;
    }

    public void buyLotto(int lottoCount) {
        int boughtLottoPrice = lottoCount * 1000;
        validateNegativeMoney(money - boughtLottoPrice);
        money -= boughtLottoPrice;
    }

    public int getPurchasableLottoCount() {
        return money / 1000;
    }

    private void validateNegativeMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException();
        }
    }

}
