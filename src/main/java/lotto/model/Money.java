package lotto.model;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양수여야 합니다.";

    private final int money;

    public Money(Integer money) {
        validatePositive(money);
        validateUnitPrice(money);
        this.money = money;
    }

    private void validatePositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE);
        }
    }

    private void validateUnitPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_UNIT_PRICE);
        }
    }

    public int getBuyableLottoSize() {
        return money / LOTTO_PRICE;
    }

    public float calculateYield(Long numerator) {
        return numerator / (float) money;
    }
}
