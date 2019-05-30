package lottogame.domain;

import lottogame.utils.InvalidLottoPriceException;

public class Money {
    private static final int MIN_NUMBER_OF_MANUAL_TICKET = 0;
    private static final int ONE_LOTTO_PRICE = 1000;

    private final int price;

    public Money(int price) {
        if (isValidatePrice(price)) {
            this.price = price;
            return;
        }
        throw new InvalidLottoPriceException("구입금액을 다시 입력해 주세요.");
    }

    private boolean isValidatePrice(int price) {
        return (price % ONE_LOTTO_PRICE == 0) && (price >= ONE_LOTTO_PRICE);
    }

    public int getNumberOfTicket() {
        return price / ONE_LOTTO_PRICE;
    }

    public boolean isValidateNumber(int numberOfManualTicket) {
        return (MIN_NUMBER_OF_MANUAL_TICKET <= numberOfManualTicket) && (numberOfManualTicket <= getNumberOfTicket());
    }
}
