package lotto.domain.rank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ranks {

    private final Map<Rank, Long> ranks;

    public Ranks(Map<Rank, Long> ranks) {
        this.ranks = new HashMap<>(ranks);
    }

    public Ranks(Ranks ranks) {
        this.ranks = ranks.unwrap();
    }

    public Long getTotalWinnings() {
        return Arrays.stream(Rank.values())
            .mapToLong(rank -> rank.getWinnings() * ranks.getOrDefault(rank, 0L))
            .sum();
    }

    public Map<Rank, Long> unwrap() {
        return new HashMap<>(ranks);
    }
}
