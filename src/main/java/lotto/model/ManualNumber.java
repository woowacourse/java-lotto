package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;

public class ManualNumber {

    private static final String MAXIMUM_MANUAL_NUMBER = "수동으로 구매할 수 있는 수가 너무 큽니다.";
    private int manualNumber;

    public ManualNumber(String input, int ticketCount) {
        int manualNumber = validateNumber(input);
        validateMaximumNumber(manualNumber, ticketCount);
        this.manualNumber = manualNumber;
    }

    private int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(Money.NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void validateMaximumNumber(int manualNumber, int ticketCount) {
        if (manualNumber > ticketCount) {
            throw new OverRangeException(MAXIMUM_MANUAL_NUMBER);
        }
    }
}
