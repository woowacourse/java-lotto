package model;

import java.util.*;
import java.util.stream.Stream;

public class LottoResult extends LinkedHashMap<LottoRank, Integer> {
    private final double earningRate;

    protected LottoResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        Stream.of(LottoRank.values()).forEach(rank -> this.put(rank, 0));
        lottos.forEach(lotto -> lotto.match(winningNumbers).map(x -> this.put(x, this.get(x) + 1)));
        this.earningRate = totalEarnings().earningRate(new Money(Lotto.PRICE * lottos.size()));
    }

    private Money totalEarnings() {
        return new Money(
                this.entrySet().stream()
                    .mapToInt(x -> x.getKey().prize().amount() * x.getValue())
                    .sum()
        );
    }

    public double earningRate() {
        return this.earningRate;
    }
}