package lotto.domain.ticket.condition;

public enum TicketSize {

    DEFAULT_SIZE(6);

    private final int size;

    TicketSize(final int size) {
        this.size = size;
    }

    public boolean doesNotMatch(final int ticketSize) {
        return ticketSize != DEFAULT_SIZE.size;
    }

    public int getSize() {
        return DEFAULT_SIZE.size;
    }

}
