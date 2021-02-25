package lottogame.domain.stats;

import java.util.*;

public class LottoResults {
    private Map<Rank, Integer> lottoResults;

    public LottoResults(Map<Rank, Integer> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public Map<Rank, Integer> values() {
        Map<Rank, Integer> lottoResults = new EnumMap<>(this.lottoResults);
        lottoResults.remove(Rank.NOT_FOUND);
        return lottoResults;
    }

    public int calculateWinningAmount() {
        return Arrays.stream(Rank.values())
                .filter(Rank::isFound)
                .mapToInt(rank -> lottoResults.get(rank) * rank.getMoney())
                .sum();
    }

    public float calculateYield(Money money) {
        return (float) calculateWinningAmount() / money.value();
    }
}
