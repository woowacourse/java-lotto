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

    public static TicketCounts from(Money purchaseMoney, int manualTicketCount) {
        int allTicketCount = purchaseMoney.calculateAllTicketCount();
        int autoTicketCount = allTicketCount - manualTicketCount;

        validateManualLottoCount(manualTicketCount);
        validateAutoLottoCount(autoTicketCount);

        return new TicketCounts(manualTicketCount, autoTicketCount);
    }

    private static void validateManualLottoCount(int manualTicketCount) {
        if (manualTicketCount <= MIN_COUNT) {
            throw new TicketCountsException("구매할 수동 로또 티켓의 수는 양수이어야 합니다.");
        }
    }

    private static void validateAutoLottoCount(int autoTicketCount) {
        if (autoTicketCount <= MIN_COUNT) {
            throw new TicketCountsException("구매 가능한 수동 로또 티켓의 수를 초과하였습니다.");
        }
    }

    public int getAutoTicketCount() {
        return autoTicketCount;
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }
}
