package lotto.domain.rank;

import java.util.HashMap;
import java.util.Map;

public class Ranks {

    private final Map<Rank, Long> ranks;

    public Ranks(Map<Rank, Long> ranks) {
        this.ranks = new HashMap<>(ranks);
    }

    public Long getWinningPrice() {
        return Rank.toList().stream()
            .mapToLong(rank -> rank.getWinnings() * ranks.get(rank))
            .sum();
    }

    public Map<Rank, Long> toMap() {
        return new HashMap<>(ranks);
    }
}
