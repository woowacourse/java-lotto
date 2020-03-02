package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;

public class LottoCount {

    private static final int MINIMUM_NUMBER = 0;
    private static final String OVER_RANGE_MANUAL_NUMBER_MESSAGE = "범위를 초과하였습니다.";
    private int autoTicketCount;
    private int manualTicketCount;

    public LottoCount(int autoTicketCount, String input) {
        int manualTicketCount = validateNumber(input);
        validateMaximumNumber(manualTicketCount, autoTicketCount);
        this.autoTicketCount = autoTicketCount;
        this.manualTicketCount = manualTicketCount;
    }

    private int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(Money.NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void validateMaximumNumber(int manualTicketCount, int autoTicketCount) {
        if (manualTicketCount < MINIMUM_NUMBER|| manualTicketCount > autoTicketCount) {
            throw new OverRangeException(OVER_RANGE_MANUAL_NUMBER_MESSAGE);
        }
    }

    public int getAutoTicketCount() {
        return autoTicketCount;
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }
}
