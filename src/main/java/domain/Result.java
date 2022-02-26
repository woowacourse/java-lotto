package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private final Map<Rank, Integer> results = new LinkedHashMap();

    public void add(Rank rank) {
        results.put(rank, results.getOrDefault(rank,0) + 1);
    }

    public Map<Rank, Integer> get() {
        return results;
    }

    public long getPrice() {
        long price = 0;
        for(Rank rank: results.keySet()){
            price += (long) rank.getPrizeMoney() * results.get(rank);
        }
        return price;
    }

    public int getRankCount(Rank rank) {
        return results.getOrDefault(rank,0);
    }
}
