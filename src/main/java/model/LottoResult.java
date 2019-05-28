package model;

import java.util.*;
import java.util.stream.Stream;

public class LottoResult implements Iterable<Map.Entry<LottoRank, Integer>> {
    private final Map<LottoRank, Integer> rankings;
    private final int purchaseAmount;

    protected LottoResult(List<Lotto> lottos, Set<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> rankings = new LinkedHashMap<LottoRank, Integer>() {{
            Stream.of(LottoRank.values()).forEach(rank -> put(rank, 0));
        }};
        lottos.forEach(lotto -> {
            LottoRank rank = lotto.match(winningNumbers, bonusNumber);
            rankings.put(rank, rankings.get(rank) + 1);
        });
        rankings.remove(LottoRank.NONE);
        this.rankings = Collections.unmodifiableMap(rankings);
        purchaseAmount = lottos.size();
    }

    public double getEarningRate() {
        return getTotalEarnings().getEarningRate(new Money(Lotto.PRICE * purchaseAmount));
    }
    private Money getTotalEarnings() {
        return new Money(
                rankings.entrySet().stream()
                .map(x -> x.getKey().getPrize().getAmount() * x.getValue())
                .reduce(0, Integer::sum)
        );
    }

    public Iterator<Map.Entry<LottoRank, Integer>> iterator() {
        return rankings.entrySet().iterator();
    }
}