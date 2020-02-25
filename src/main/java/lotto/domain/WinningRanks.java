package lotto.domain;

import java.util.Map;

public class WinningRanks {
    private final Map<Rank, Integer> winningRanks;

    WinningRanks(Map<Rank, Integer> winningRanks) {
        this.winningRanks = winningRanks;
    }

    public Map<Rank, Integer> getWinningRanks() {
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
