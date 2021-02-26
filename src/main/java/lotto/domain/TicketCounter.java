package lotto.domain;

public class TicketCounter {

    public static final String TOTAL_TICKET_ZERO_LOWER_ERROR_MESSAGE = "총 티켓은 0장 이하가 될 수 없습니다.";
    private final int count;

    public TicketCounter(final int count) {
        this.count = count;
    }

    public TicketCounter(final Ticket ticket) {
        this.count = ticket.getCount();
    }

    public TicketCounter minus(final int count) {
        if (this.count >= count) {
            return new TicketCounter(this.count - count);
        }
        throw new IllegalArgumentException(TOTAL_TICKET_ZERO_LOWER_ERROR_MESSAGE);
    }

    public boolean isZero() {
        return count == 0;
    }

    public int getCount() {
        return count;
    }
}
