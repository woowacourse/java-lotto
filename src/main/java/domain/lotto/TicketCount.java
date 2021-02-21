package domain.lotto;

public class TicketCount {
    private static final int ZERO = 0;

    private final int ticketCount;

    private TicketCount(final int ticketCount) {
        validateLottoCount(ticketCount);
        this.ticketCount = ticketCount;
    }

    public static TicketCount of(final int lottoCount) {
        return new TicketCount(lottoCount);
    }

    private void validateLottoCount(final int lottoCount) {
        if (lottoCount <= ZERO) {
            throw new IllegalArgumentException("최소 한 개의 로또를 구매해야 합니다.");
        }
    }

    public int getTicketCount() {
        return ticketCount;
    }
}
