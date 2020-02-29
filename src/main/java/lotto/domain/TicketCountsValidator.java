package lotto.domain;

import lotto.exception.TicketCountsException;

public class TicketCountsValidator {
    private static final int MIN_COUNT = 0;

    private TicketCountsValidator() {
    }

    public static void validateManualTicketCount(int allTicketCount, int manualTicketCount) {
        validateCountIsLessThanMax(allTicketCount, manualTicketCount);
        validateCountIsGreaterThantMin(manualTicketCount);
    }

    private static void validateCountIsLessThanMax(int allTicketCount, int manualTicketCount) {
        if (allTicketCount < manualTicketCount) {
            throw new TicketCountsException("구매 가능한 로또 티켓의 수를 초과하였습니다.");
        }
    }

    private static void validateCountIsGreaterThantMin(int manualTicketCount) {
        if (manualTicketCount <= MIN_COUNT) {
            throw new TicketCountsException("구매할 로또 티켓의 수는 양수이어야 합니다.");
        }
    }
}
