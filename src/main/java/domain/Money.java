package domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_REMAIN_MONEY_NEGATIVE = "[ERROR] 현재 보유금액이 0원 미만입니다.";

    private int remainMoney;
    private int totalMoney;

    public Money(int money) {
        validateNegativeMoney(money);
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

    public double calculateProfitRate(long sumTotalPrice) {
        return sumTotalPrice / (double)totalMoney;
    }

    private void validateNegativeMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(ERROR_REMAIN_MONEY_NEGATIVE);
        }
    }
}
