package lotto.domain;

import lotto.util.InputValidationUtil;

public class TicketingCount {
    private int ticketingCount;

    public TicketingCount(String TicketingCount) {
        this.ticketingCount = InputValidationUtil.returnNumberWithNumberCheck(TicketingCount);
        InputValidationUtil.isPositiveNumber(this.ticketingCount);
    }

    public int getTicketingCount() {
        return ticketingCount;
    }
}
