package model;

import java.util.*;
import java.util.stream.Stream;

public class LottoResult implements Iterable<Map.Entry<LottoRank, Integer>> {
    private final Map<LottoRank, Integer> rankings;
    private final double earningRate;

    protected LottoResult(List<Lotto> lottos) {
        this.rankings = processResult(lottos);
        this.earningRate = getTotalEarnings().getEarningRate(new Money(Lotto.PRICE * lottos.size()));
    }

    private Map<LottoRank, Integer> processResult(List<Lotto> lottos) {
        Map<LottoRank, Integer> result = new LinkedHashMap<LottoRank, Integer>() {{
            Stream.of(LottoRank.values()).forEach(rank -> put(rank, 0));
        }};
        lottos.forEach(lotto -> lotto.match().map(x -> result.put(x, result.get(x) + 1)));
        return Collections.unmodifiableMap(result);
    }

    private Money getTotalEarnings() {
        return new Money(
                rankings.entrySet().stream()
                .mapToInt(x -> x.getKey().getPrize().getAmount() * x.getValue())
                .sum()
        );
    }

    public double getEarningRate() {
        return earningRate;
    }

    public Iterator<Map.Entry<LottoRank, Integer>> iterator() {
        return rankings.entrySet().iterator();
    }
}