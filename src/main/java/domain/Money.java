package domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_AMOUNT_MINIMUM = 1;

    private static final String LOTTO_AMOUNT_ERROR = "[ERROR]: 금액이 부족해 로또를 구입할 수 없습니다. (로또는 안사는게 이득임.)";

    private final int money;
    private int lottoAmount;

    public Money(int money) {
        this.money = money;
    }

    public int money() {
        return this.money;
    }

    public int convertToAmount() {
        lottoAmount = money / LOTTO_PRICE;
        checkLottoAmount();
        return lottoAmount;
    }

    public boolean isAffordable(int amount) {
        return convertToAmount() >= amount;
    }

    private void checkLottoAmount() {
        if (lottoAmount < LOTTO_AMOUNT_MINIMUM) {
            throw new IllegalArgumentException(LOTTO_AMOUNT_ERROR);
        }
    }

}
