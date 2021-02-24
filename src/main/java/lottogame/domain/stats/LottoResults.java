package lottogame.domain.stats;

import lottogame.domain.Money;
import lottogame.domain.Rank;

import java.util.*;

public class LottoResults {
    private Map<Rank, Integer> lottoResults;

    public LottoResults(Map<Rank, Integer> lottoResults) {
        lottoResults.remove(Rank.NOT_FOUND);
        this.lottoResults = lottoResults;
    }

    public Map<Rank, Integer> values() {
        return new EnumMap<>(lottoResults);
    }

    public int calculateWinningAmount() {
        return Arrays.stream(Rank.values())
                .filter(Rank::isFound)
                .mapToInt(rank -> lottoResults.get(rank) * rank.getMoney())
                .sum();
    }

    public float calculateYield(Money money) {
        return (float) calculateWinningAmount() / money.getMoney();
    }
}
