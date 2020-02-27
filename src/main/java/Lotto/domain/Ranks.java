package Lotto.domain;

import java.util.List;
import java.util.Objects;

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
