package lotto.model;

import lotto.exception.NotNumberException;

public class ManualNumber {
    private int manualNumber;

    public ManualNumber(String input) {
        int manualNumber = validateNumber(input);
    }

    private int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(Money.NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
