package lotto.model;

import lotto.exception.OverRangeException;

public class TicketNumber {
    public static final String OVER_RANGE_EXCEPTION_MESSAGE = "금액의 한도를 초과하였습니다.";
    private int totalTicket;
    private int manualTicket;
    private int autoTicket;

    public TicketNumber(int totalTicket, int manualTicket) {
        checkOverPay(totalTicket, manualTicket);
        this.totalTicket = totalTicket;
        this.manualTicket = manualTicket;
        this.autoTicket = totalTicket - manualTicket;
    }

    public void checkOverPay(int totalTicket, int manualTicket) {
        if (totalTicket < manualTicket) {
            throw new OverRangeException(OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public int getManualTicket() {
        return manualTicket;
    }

    public int getAutoTicket() {
        return autoTicket;
    }
}
