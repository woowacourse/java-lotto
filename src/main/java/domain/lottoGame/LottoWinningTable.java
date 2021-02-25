package domain.lottoGame;

import domain.Money;

import java.util.HashMap;
import java.util.Map;

public class LottoWinningTable {

    private final Map<LottoRank, Long> winningTable;

    public LottoWinningTable(final Map<LottoRank, Long> results) {
        winningTable = new HashMap<>(results);
    }

    public Money getTotalWinningMoney() {
        Money total = Money.ZERO;
        for (LottoRank lottoRank : winningTable.keySet()) {
            long count = winningTable.get(lottoRank);
            total = total.add(lottoRank.getPrize(count));
        }

        return total;
    }

    public Map<LottoRank, Long> getValues() {
        return new HashMap<>(winningTable);
    }
}
