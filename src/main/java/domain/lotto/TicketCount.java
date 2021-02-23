package domain.lotto;

public class TicketCount {
    private static final int ZERO = 0;
    private static final String TICKET_MINIMUM_SIZE_EXCEPTION_MESSAGE = "최소 한 개의 로또를 구매해야 합니다. 현재 갯수: %d";

    private final int ticketCount;

    public TicketCount(final int ticketCount) {
        validateLottoCount(ticketCount);
        this.ticketCount = ticketCount;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    private void validateLottoCount(final int lottoCount) {
        if (lottoCount <= ZERO) {
            throw new IllegalArgumentException(String.format(TICKET_MINIMUM_SIZE_EXCEPTION_MESSAGE, lottoCount));
        }
    }
}
