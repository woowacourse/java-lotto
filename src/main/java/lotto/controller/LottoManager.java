package lotto.controller;

import lotto.model.LottoResultCount;
import lotto.model.Ticket;
import lotto.model.Tickets;
import lotto.model.WinLottoNumbers;

public class LottoManager {
    public static LottoResultCount lotto(Tickets tickets, WinLottoNumbers winLottoNumbers) {
        LottoResultCount lottoResultCount = new LottoResultCount();
        for (Ticket ticket : tickets.getTickets()) {
            lottoResultCount.resultCount(ticket, winLottoNumbers);
        }
        return lottoResultCount;
    }
}
