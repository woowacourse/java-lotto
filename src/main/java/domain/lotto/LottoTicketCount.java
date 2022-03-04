package domain.lotto;

import exception.ticketCount.CountLessZeroException;
import exception.ticketCount.CountMoreMaxException;

public class LottoTicketCount {
    private final int manualValue;
    private final int autoValue;

    private LottoTicketCount(final int manualValue, final int autoValue) {
        validate(manualValue, autoValue);
        this.manualValue = manualValue;
        this.autoValue = autoValue;
    }

    public static LottoTicketCount of(final int fullValue, final int manualValue) {
        return new LottoTicketCount(manualValue, fullValue - manualValue);
    }

    private static void validate(final int manualValue, final int autoValue) {
        if (autoValue < 0) {
            throw new CountMoreMaxException();
        }
        if (manualValue < 0 || manualValue + autoValue <= 0) {
            throw new CountLessZeroException();
        }
    }

    public int ofManual() {
        return manualValue;
    }

    public int ofAuto() {
        return autoValue;
    }
}