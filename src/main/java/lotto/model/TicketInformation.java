package lotto.model;

import lotto.exception.OverRangeException;

import java.util.List;

public class TicketInformation {
    public static final String OVER_RANGE_EXCEPTION_MESSAGE = "금액의 한도를 초과하였습니다.";
    private int manualTicketCount;
    private int autoTicketCount;
    private List<LottoTicket> manualTickets;

    public TicketInformation(int totalTicket, int manualTicketCount, List<LottoTicket> manualTickets) {
        checkOverPay(totalTicket, manualTicketCount);
        this.manualTicketCount = manualTicketCount;
        this.autoTicketCount = totalTicket - manualTicketCount;
        this.manualTickets = manualTickets;
    }

    public void checkOverPay(int totalTicket, int manualTicket) {
        if (totalTicket < manualTicket) {
            throw new OverRangeException(OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }

    public int getAutoTicketCount() {
        return autoTicketCount;
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }
}
