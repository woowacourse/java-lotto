package lotto.controller;

import lotto.model.*;

public class LottoManager {

    public static void lotto(Tickets tickets, WinNumbers winNumbers) {
        for (Ticket ticket : tickets.getTickets()) {
            RankType rankType = RankType.findLottoResult(winNumbers.matchCount(ticket),
                ticket.contains(winNumbers.getBonusBallNumber()));
            LottoResult.updateCount(rankType);
        }
    }
}