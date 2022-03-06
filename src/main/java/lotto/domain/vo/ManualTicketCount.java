package lotto.domain.vo;

import java.util.Objects;

public class ManualTicketCount {

    static final String NOT_NUMBER_MESSAGE = "[ERROR] 구입금액은 숫자여야 합니다.";
    public static final String NOT_BUY_MANUAL_MESSAGE = "[ERROR] 구입 가능한 액수를 벗어납니다.";
    private int value;

    public ManualTicketCount(String ticketCount, PurchaseAmount purchaseAmount) {
        validateIsNumber(ticketCount);
        this.value = Integer.parseInt(ticketCount);
        validateCanBuy(purchaseAmount);
    }

    public int ticketNumber() {
        return value;
    }

    private void validateIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NOT_NUMBER_MESSAGE);
        }
    }

    private void validateCanBuy(PurchaseAmount purchaseAmount) {
        if (!canBuy(purchaseAmount)) {
            throw new IllegalArgumentException(NOT_BUY_MANUAL_MESSAGE);
        }
    }

    private boolean canBuy(PurchaseAmount purchaseAmount) {
        int canBuyNumber = purchaseAmount.availableTicketCanBuy();
        return value <= canBuyNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManualTicketCount that = (ManualTicketCount) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ManualTicketNumber{" +
                "ticketNumber=" + value +
                '}';
    }
}
