package domain.lotto;

import exception.ticketCount.CountLessZeroException;
import exception.ticketCount.CountMoreMaxException;

public class LottoTicketCount {
    private final int manualTicketCount;
    private final int autoTicketCount;

    private LottoTicketCount(final int manualTicketCount, final int autoTicketCount) {
        validate(manualTicketCount, autoTicketCount);
        this.manualTicketCount = manualTicketCount;
        this.autoTicketCount = autoTicketCount;
    }

    public static LottoTicketCount of(final int fullTicketCount, final int manualTicketCount) {
        return new LottoTicketCount(manualTicketCount, fullTicketCount - manualTicketCount);
    }

    private static void validate(final int manualTicketCount, final int autoTicketCount) {
        if (autoTicketCount < 0) {
            throw new CountMoreMaxException();
        }
        if (manualTicketCount < 0 || manualTicketCount + autoTicketCount <= 0) {
            throw new CountLessZeroException();
        }
    }

    public int ofManual() {
        return manualTicketCount;
    }

    public int ofAuto() {
        return autoTicketCount;
    }
}