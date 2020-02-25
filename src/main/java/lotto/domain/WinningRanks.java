package lotto.domain;

import java.util.Map;

public class WinningRanks {
    private static final int WINNING_AMOUNT = 1;
    private static final int DEFAULT_WINNING_TICKET_SIZE = 0;
    private Map<Rank, Integer> winningRanks;

    WinningRanks(Map<Rank, Integer> winningRanks) {
        this.winningRanks = winningRanks;
    }

    public Map<Rank, Integer> getWinningRanks() {
        return winningRanks;
    }

    /* winningTicketSize은 각 Rank별 당첨된 티켓 수를 의미한다. */
    Map<Rank, Integer> addWinningRanks(Rank rank) {
        if (rank == null) {
            return winningRanks;
        }

        Integer winningTicketSize = winningRanks.get(rank);
        if (winningTicketSize == null) {
            winningTicketSize = DEFAULT_WINNING_TICKET_SIZE;
        }

        winningRanks.put(rank, winningTicketSize + WINNING_AMOUNT);
        return winningRanks;
    }

    Money calculateTotalWinningMoney() {
        Money totalWinningMoney = new Money();
        for (Rank rank : winningRanks.keySet()) {
            int size = winningRanks.get(rank);
            Money winningMoney = rank.calculateWinningMoney().multiply(size);
            totalWinningMoney = totalWinningMoney.add(winningMoney);
        }
        return totalWinningMoney;
    }

}
