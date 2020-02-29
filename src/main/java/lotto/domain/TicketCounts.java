package lotto.domain;

import lotto.exception.TicketCountsException;

public class TicketCounts {
    private static final int MIN_COUNT = 0;

    private final int manualTicketCount;
    private final int autoTicketCount;

    private TicketCounts(int manualTicketCount, int autoTicketCount) {
        this.manualTicketCount = manualTicketCount;
        this.autoTicketCount = autoTicketCount;
    }

    public static TicketCounts fromMoneyAndManualTicketCount(Money purchaseMoney, int manualTicketCount) {
        int allTicketCount = purchaseMoney.calculateQuotient(Money.ofTicketPrice());
        int autoTicketCount = allTicketCount - manualTicketCount;

        validateManualTicketCount(allTicketCount, manualTicketCount);

        return new TicketCounts(manualTicketCount, autoTicketCount);
    }

    private static void validateManualTicketCount(int allTicketCount, int manualTicketCount) {
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

    public int getAutoTicketCount() {
        return autoTicketCount;
    }
}
