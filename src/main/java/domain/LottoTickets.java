package domain;

import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoResults match(WinningLottoTicket winningLottoTicket) {
        LottoResults lottoResults = new LottoResults();

        lottoTickets.stream()
                .map(lottoTicket -> new LottoResult(lottoTicket.getCorrectCount(
                        winningLottoTicket.getWinningTicket().getLottoTicket()), winningLottoTicket.isMatchBonusBall(lottoTicket)))
                .forEach(lottoResults::add);

        return lottoResults;
    }
}
