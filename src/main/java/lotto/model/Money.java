package lotto.model;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";

    private int money;

    public Money(Integer money) {
        validateUnitPrice(money);
        this.money = money;
    }

    private static void validateUnitPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_UNIT_PRICE);
        }
    }

    public int getLottoSize() {
        return money / LOTTO_PRICE;
    }

    public float calculatePercentage(Long numerator) {
        return numerator / (float) money;
    }
}
