package lotto.controller;

import lotto.model.*;

public class LottoManager {

    public static void lotto(Tickets tickets, WinNumbers winNumbers) {
        for (Ticket ticket : tickets.getTickets()) {
            LottoResult lottoResult = LottoResult.findLottoResult(winNumbers.matchCount(ticket),
                ticket.contains(winNumbers.getBonusBallNumber()));
            LottoResultCount.updateCount(lottoResult);
        }
    }
}