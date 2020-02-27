package Lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ranks {
    private List<Rank> ranks;

    public Ranks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public int addAllRankReward() {
        return ranks.stream()
                .filter(Objects::nonNull)
                .mapToInt(Rank::getRankReward)
                .sum();
    }

    public Map<Rank, Long> countRanks() {
        Map<Rank, Long> rankCounts = ranks.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        for (Rank rank : Rank.values()) {
            rankCounts.putIfAbsent(rank, 0L);
        }
        return rankCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranks ranks1 = (Ranks) o;
        return Objects.equals(ranks, ranks1.ranks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranks);
    }
}
