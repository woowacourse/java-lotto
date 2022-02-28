package domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;

    private int remainMoney;
    private int totalMoney;

    public Money(int money) {
        this.totalMoney = money;
        this.remainMoney = money;
    }

    public void buyLotto(int lottoCount) {
        int boughtLottoPrice = lottoCount * LOTTO_PRICE;
        validateNegativeMoney(remainMoney - boughtLottoPrice);
        remainMoney -= boughtLottoPrice;
    }

    public int getPurchasableLottoCount() {
        return remainMoney / LOTTO_PRICE;
    }

    private void validateNegativeMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException();
        }
    }

    public double calculateProfitRate(long sumTotalPrice) {
        return sumTotalPrice / (double)totalMoney;
    }
}
