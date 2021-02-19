package lotto.domain.rank;

import java.util.HashMap;
import java.util.Map;

public class Ranks {

    private final Map<Rank, Long> ranks;

    public Ranks(Map<Rank, Long> ranks) {
        this.ranks = new HashMap<>(ranks);
    }

    public Long getWinningPrice() {
        return Rank.getAllPossibleRanks().stream()
            .mapToLong(rank -> rank.getWinnings() * ranks.get(rank))
            .sum();
    }

    public Map<Rank, Long> unbox() {
        return new HashMap<>(ranks);
    }
}
