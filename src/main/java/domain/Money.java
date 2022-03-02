package domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_REMAIN_MONEY_NEGATIVE = "[ERROR] 현재 보유금액이 0원 미만입니다.";

    private final int remainMoney;
    private final int totalMoney;

    public Money(int money, int boughtLottoCount) {
        int boughtLottoPrice = boughtLottoCount * LOTTO_PRICE;
        validateNegativeMoney(money - boughtLottoPrice);
        this.totalMoney = money;
        this.remainMoney = money - boughtLottoPrice;
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
