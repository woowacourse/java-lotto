package model;

import java.util.*;

public class LottoResult implements Iterable<Map.Entry<LottoRank, Integer>> {
    private final LottoResultTable table;
    private final double earningRate;

    protected LottoResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        this.table = new LottoResultTable(lottos, winningNumbers);
        this.earningRate = this.table.totalEarnings().earningRate(new Money(Lotto.PRICE * lottos.size()));
    }

    public double earningRate() {
        return this.earningRate;
    }

    @Override
    public Iterator<Map.Entry<LottoRank, Integer>> iterator() {
        return table.iterator();
    }
}