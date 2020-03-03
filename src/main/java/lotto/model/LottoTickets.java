package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets() {
        lottoTickets = new ArrayList<>();
    }

    public void combineTickets(TicketNumber ticketNumber) {
        ManualTicket manualTicket = new ManualTicket();
        lottoTickets.addAll(manualTicket.generate(ticketNumber));
        AutoTicket autoTicket = new AutoTicket();
        lottoTickets.addAll(autoTicket.generate(ticketNumber));
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public LottoResult matchLottoResult(WinNumber winNumber, BonusBall bonusBall) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket lottoTicket : lottoTickets) {
            lottoResult.checkCount(lottoTicket, winNumber, bonusBall);
        }
        return lottoResult;
    }
}
