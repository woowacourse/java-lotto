package lotto.controller;

import lotto.model.LottoResult;
import lotto.model.Ticket;
import lotto.model.Tickets;
import lotto.model.WinLottoNumbers;

public class LottoManager {
    public static LottoResult lotto(Tickets tickets, WinLottoNumbers winLottoNumbers) {
        LottoResult lottoResult = new LottoResult();
        for (Ticket ticket : tickets.getTickets()) {
            lottoResult.resultCount(ticket, winLottoNumbers);
        }
        return lottoResult;
    }
}
