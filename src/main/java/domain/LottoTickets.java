package domain;

import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoResults match(WinningLottoTicket winningLottoTicket) {
        LottoResults lottoResults = new LottoResults();
        lottoTickets.forEach(lottoTicket -> {
            lottoTicket.getCorrectCount(winningLottoTicket.winningTicket)
        });

        return lottoResults;
    }


}
