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

    public void add(Rank rank) {
        if (rank.isNotFound()) {
            return;
        }
        lottoResults.put(rank, lottoResults.get(rank) + 1);
    }

    public Map<Rank, Integer> values() {
        return new EnumMap<>(lottoResults);
    }

    public int calculateWinningAmount() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isFound())
                .mapToInt(rank -> lottoResults.get(rank) * rank.getMoney())
                .sum();
    }

    public float calculateYield(Money money) {
        System.out.println(calculateWinningAmount());
        System.out.println(money.getMoney());
        return (float) calculateWinningAmount() / money.getMoney();
    }
}
