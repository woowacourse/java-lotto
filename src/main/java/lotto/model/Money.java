package lotto.model;

public class Money {

    private static final int PRICE_PER_LOTTO = 1000;

    private static final String PRICE_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";

    private int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) throws RuntimeException {
        if (money < 0) {
            throw new RuntimeException(PRICE_ERROR_MESSAGE);
        }
    }

    public int getBuyingLottoCount() {
        return money / PRICE_PER_LOTTO;
    }
}
