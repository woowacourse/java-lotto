package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1_000;
    private static final String LACK_OF_MONEY_MESSAGE = "돈이 부족해 로또를 살 수 없습니다.";

    private int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(LACK_OF_MONEY_MESSAGE);
        }
    }

    public int calculateLottoTicketCount() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
