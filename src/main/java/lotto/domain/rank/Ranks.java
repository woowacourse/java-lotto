package lotto.domain.rank;

import java.util.HashMap;
import java.util.Map;

public class Ranks {

    private final Map<Rank, Long> ranks;

    public Ranks(Map<Rank, Long> ranks) {
        this.ranks = new HashMap<>(ranks);
    }

    public Long getRanksCount() {
        return ranks.keySet().stream()
            .mapToLong(rank -> ranks.get(rank))
            .sum();
    }

    public Long getTotalWinnings() {
        return ranks.keySet().stream()
            .mapToLong(rank -> ranks.get(rank) * rank.getWinnings())
            .sum();
    }

    public Map<Rank, Long> unwrap() {
        return new HashMap<>(ranks);
    }
}
