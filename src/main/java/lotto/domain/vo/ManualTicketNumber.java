package lotto.domain.vo;

import java.util.Objects;

public class ManualTicketNumber {

    static final String NOT_NUMBER_MESSAGE = "[ERROR] 구입금액은 숫자여야 합니다.";
    public static final String NOT_BUY_MANUAL_MESSAGE = "[ERROR] 구입 가능한 액수를 벗어납니다.";
    private Integer ticketNumber;

    public ManualTicketNumber(String ticketNumber, PurchaseAmount purchaseAmount) {
        validateIsNumber(ticketNumber);
        this.ticketNumber = Integer.parseInt(ticketNumber);
        validateCanBuy(purchaseAmount);
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
        int canBuyNumber = purchaseAmount.getAvailableTicketNumber();
        return ticketNumber <= canBuyNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManualTicketNumber that = (ManualTicketNumber) o;
        return Objects.equals(ticketNumber, that.ticketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNumber);
    }

    @Override
    public String toString() {
        return "ManualTicketNumber{" +
                "ticketNumber=" + ticketNumber +
                '}';
    }
}
