package domain.lotto;

import domain.budget.Budget;

public class TicketCount {
    private static final int ZERO = 0;

    private final int ticketCount;

    private TicketCount(int ticketCount) {
        validateLottoCount(ticketCount);
        this.ticketCount = ticketCount;
    }

    public static TicketCount of(int lottoCount) {
        return new TicketCount(lottoCount);
    }

    public static TicketCount of(Budget budget) {
        return new TicketCount(budget.getIntValue());
    }

    private void validateLottoCount(int lottoCount) {
        if (lottoCount <= ZERO) {
            throw new IllegalArgumentException("최소 한 개의 로또를 구매해야 합니다.");
        }
    }

    public int getTicketCount() {
        return ticketCount;
    }
}
