package domain;

import static domain.LottoTicket.LOTTO_PRICE;

import view.InputErrorMessage;

public class Money {
    private int money;

    public Money(int money) {
        validateLottoPriceUnit(money);
        this.money = money;
    }

    private static void validateLottoPriceUnit(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(InputErrorMessage.NOT_DIVIDED_BY_1000.getMessage());
        }
    }

    public int getMoney() {
        return money;
    }
}
