package lotto.model;

import lotto.exception.OverRangeException;

import java.util.List;

public class TicketInformation {
    private static final String OVER_RANGE_EXCEPTION_MESSAGE = "금액의 한도를 초과하였습니다.";
    private static final String MANUAL_TICKETS_NULL_POINTER_EXCEPTION_MESSAGE = "수동 로또가 null 값입니다.";
    private int manualTicketCount;
    private int autoTicketCount;
    private List<LottoTicket> manualTickets;

    public TicketInformation(Payment payment, int manualTicketCount, List<LottoTicket> manualTickets) {
        checkOverPay(payment.countTickets(), manualTicketCount);
        checkNullManualTickets(manualTickets);
        this.manualTicketCount = manualTicketCount;
        this.autoTicketCount = payment.countTickets() - manualTicketCount;
        this.manualTickets = manualTickets;
    }

    private void checkNullManualTickets(List<LottoTicket> manualTickets) {
        if (manualTickets == null) {
            throw new NullPointerException(MANUAL_TICKETS_NULL_POINTER_EXCEPTION_MESSAGE);
        }
    }

    public void checkOverPay(int totalTicket, int manualTicket) {
        if (totalTicket < manualTicket) {
            throw new OverRangeException(OVER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public int getAutoTicketCount() {
        return autoTicketCount;
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }
}
