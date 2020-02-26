package domain;

public class LottoManager {
    public static LottoResults match(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        return new LottoResults(lottoTickets, winningLottoTicket);
    }
}
