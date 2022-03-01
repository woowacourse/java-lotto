package lotto.domain;

import lotto.exception.MoneyException;

public class Money {

    private static final int UNIT_SIZE = 1000;

    private final int money;

    public Money(int input) {
        validateUnit(input);
        this.money = input;
    }

    private static void validateUnit(int input) {
        if (!isCorrectUnit(input)) {
            throw new MoneyException(MoneyException.MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectUnit(int input) {
        return input % UNIT_SIZE == 0;
    }

    public int getMoney() {
        return money;
    }

    public int calculateTotalLottoCount(int lottoTicketPrice) {
        return money / lottoTicketPrice;
    }
}
