package domain;

public class LottoManager {
    public static LottoResults match(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        LottoResults lottoResults = new LottoResults();
        lottoTickets.getLottoTickets()
                .forEach(lottoTicket -> lottoResults.putLottoResults(lottoTicket, winningLottoTicket));
        return lottoResults;
    }
}
