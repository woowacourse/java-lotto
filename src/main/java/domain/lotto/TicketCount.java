package domain.lotto;

import java.util.Objects;

public class TicketCount {
    private static final int ZERO = 0;
    private static final String TICKET_MINIMUM_SIZE_EXCEPTION_MESSAGE = "최소 한 개의 로또를 구매해야 합니다. 현재 갯수: %d";
    private static final String MANUAL_TICKET_SIZE_EXCEPTION_MESSAGE = "수동으로 구매가능한 갯수가 아닙니다 구매가능 티켓수: %d 현재 입력:%d";

    private final int ticketCount;

    public TicketCount(final int ticketCount) {
        validateLottoCount(ticketCount);
        this.ticketCount = ticketCount;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    private void validateLottoCount(final int lottoCount) {
        if (lottoCount < ZERO) {
            throw new IllegalArgumentException(String.format(TICKET_MINIMUM_SIZE_EXCEPTION_MESSAGE, lottoCount));
        }
    }

    public TicketCount reduceTicketCount(int manualTicketCount) {
        if (isAvailableCount(manualTicketCount)) {
            return new TicketCount(this.ticketCount - manualTicketCount);
        }
        throw new IllegalArgumentException(String.format(MANUAL_TICKET_SIZE_EXCEPTION_MESSAGE, ticketCount, manualTicketCount));
    }

    private boolean isAvailableCount(int manualTicketCount) {
        return manualTicketCount >= 0 && manualTicketCount <= ticketCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCount that = (TicketCount) o;
        return ticketCount == that.ticketCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketCount);
    }
}
