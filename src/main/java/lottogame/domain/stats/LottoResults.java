package lottogame.domain.stats;

import java.util.*;

public class LottoResults {
    private final Map<Rank, Integer> lottoResults;

    public LottoResults(Map<Rank, Integer> lottoResults) {
        lottoResults.remove(Rank.NOT_FOUND);
        this.lottoResults = lottoResults;
    }

    public Map<Rank, Integer> values() {
        return new EnumMap<>(this.lottoResults);
    }

    public Money totalPrizeMoney() {
        int prizeMoney = Arrays.stream(Rank.values())
                .filter(Rank::isFound)
                .mapToInt(rank -> lottoResults.get(rank) * rank.getMoney())
                .sum();
        return Money.of(prizeMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResults that = (LottoResults) o;
        return Objects.equals(lottoResults, that.lottoResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResults);
    }
}
