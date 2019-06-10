package lottogame.domain;

import lottogame.lottogameexception.InvalidLottoPriceException;

public class Money {
    private static final int MIN_NUMBER_OF_MANUAL_TICKET = 0;
    private static final int PERCENTAGE = 100;
    private static final int ONE_LOTTO_PRICE = 1000;

    private final int price;

    public Money(int price) {
        checkValidPrice(price);
        this.price = price;
    }

    private void checkValidPrice(int price) {
        if ((price % ONE_LOTTO_PRICE == 0) && (price >= ONE_LOTTO_PRICE)) {
            throw new InvalidLottoPriceException("구입금액을 다시 입력해 주세요.");
        }
    }

    public int getNumberOfTicket() {
        return price / ONE_LOTTO_PRICE;
    }

    boolean isValidateAmount(int numberOfManualTicket) {
        return (MIN_NUMBER_OF_MANUAL_TICKET <= numberOfManualTicket) && (numberOfManualTicket <= getNumberOfTicket());
    }

    long rateOf(double profits) {
        return (long) (profits / price) * PERCENTAGE;
    }
}
