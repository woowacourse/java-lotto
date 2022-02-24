package domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private final LinkedHashMap<Rank, Integer> result = new LinkedHashMap<>();

    public void add(Rank rank) {
        result.put(rank, result.getOrDefault(rank, 0) + 1);
    }

    public Map<Rank, Integer> get() {
        return result;
    }

    public long getPrize() {
        long prize = 0;
        for (Rank rank : result.keySet()) {
            prize += (long) rank.getPrize() * result.get(rank);
        }
        return prize;
    }

    public int getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0);
    }
}
