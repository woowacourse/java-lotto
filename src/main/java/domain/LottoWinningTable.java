package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoWinningTable {

    private final Map<LottoRank, Long> winningTable;

    public LottoWinningTable(final Map<LottoRank, Long> results) {
        winningTable = new HashMap<>(results);
    }

    public Money getTotalWinningMoney() {
        Money total = new Money(0);
        for (LottoRank lottoRank : winningTable.keySet()) {
            Money prize = lottoRank.getPrize();
            long count = winningTable.get(lottoRank);

            total = total.add(prize.multiply(count));
        }

        return total;
    }

    public Map<LottoRank, Long> getValues() {
        return new HashMap<>(winningTable);
    }
}
