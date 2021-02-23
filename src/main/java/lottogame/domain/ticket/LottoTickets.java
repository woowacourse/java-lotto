package lottogame.domain.ticket;

import lottogame.domain.machine.LottoWinningMachine;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    public static final int TICKET_PRICE = 1000;
    private int autoTicketCount;

    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public void add(final LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
        countAutoTicket(lottoTicket);
    }

    private void countAutoTicket(final LottoTicket lottoTicket) {
        if (lottoTicket.isAutoTicket()) {
            autoTicketCount++;
        }
    }

    public List<LottoTicket> toList() {
        return new ArrayList<>(lottoTickets);
    }

    public int getTicketsCount() {
        return lottoTickets.size();
    }

    public int getAutoTicketsCount() {
        return autoTicketCount;
    }

    public int getManualTicketsCount() {
        return lottoTickets.size() - autoTicketCount;
    }

    public int getCostUsedToBuy() {
        return lottoTickets.size() * TICKET_PRICE;
    }

    public List<LottoTicketResult> getLottoTicketResults(final LottoWinningMachine lottoWinningMachine) {
        List<LottoTicketResult> lottoTicketResults = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            int matchedCount = lottoWinningMachine.countMatchedWinningNumber(lottoTicket);
            boolean isBonusMatch = lottoWinningMachine.isMatchBonusNumber(lottoTicket);
            lottoTicketResults.add(new LottoTicketResult(matchedCount, isBonusMatch));
        }
        return lottoTicketResults;
    }
}
