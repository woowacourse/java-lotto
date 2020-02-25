package lotto.domain;

import lotto.util.InputValidationUtil;

public class TicketingCount {
    private int ticketingCount;

    public TicketingCount(String ticketingCount) {
        this.ticketingCount = InputValidationUtil.returnNumberWithNumberCheck(ticketingCount);
        InputValidationUtil.isPositiveNumber(this.ticketingCount);
    }

    public int getTicketingCount() {
        return ticketingCount;
    }
}
