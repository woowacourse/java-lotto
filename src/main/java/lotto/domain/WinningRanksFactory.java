package lotto.domain;

import java.util.HashMap;
import java.util.Map;

class WinningRanksFactory {
    private static final int DEFAULT_WINNING_TICKET_SIZE = 0;
    private static final int WINNING_AMOUNT = 1;

    static WinningRanks create(Ranks ranks) {
        Map<Rank, Integer> winningRanks = new HashMap<>();
        for (Rank rank : ranks.getRanks()) {
            Integer winningTicketSize = winningRanks.get(rank);
            if (winningTicketSize == null) {
                winningTicketSize = DEFAULT_WINNING_TICKET_SIZE;
            }
            winningRanks.put(rank, winningTicketSize + WINNING_AMOUNT);
        }
        return new WinningRanks(winningRanks);
    }
}
