package domain;

import exception.ExceptionMessage;

public class LottoOrder {
    private final int manualTicketCount;
    private final int autoTicketCount;

    public LottoOrder(Payment payment, int manualTicketCount, int autoTicketCount) {
        validateLottoOrder(payment.calculateLottoCount(), manualTicketCount, autoTicketCount);
        this.manualTicketCount = manualTicketCount;
        this.autoTicketCount = autoTicketCount;
    }

    private static void validateLottoOrder(int totalLottoTicketCount, int manualTicketCount, int autoTicketCount) {
        validateTicketCount(manualTicketCount);
        validateTicketCount(autoTicketCount);
        validateManualTicketCount(totalLottoTicketCount, manualTicketCount);
        validateTotalTicketCount(totalLottoTicketCount, manualTicketCount, autoTicketCount);
    }

    private static void validateTicketCount(int ticketCount) {
        if (ticketCount < 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_TICKET_COUNT.getMessage());
        }
    }

    private static void validateManualTicketCount(int totalLottoTicketCount, int manualTicketCount) {
        if (totalLottoTicketCount < manualTicketCount) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MANUAL_TICKET_COUNT.getMessage());
        }
    }

    private static void validateTotalTicketCount(int totalLottoTicketCount,
                                                 int manualTicketCount,
                                                 int autoTicketCount) {
        if (totalLottoTicketCount != manualTicketCount + autoTicketCount) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_TOTAL_TICKET_COUNT.getMessage());
        }
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }

    public int getAutoTicketCount() {
        return autoTicketCount;
    }
}
