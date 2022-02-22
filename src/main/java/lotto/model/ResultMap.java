package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {
    Map<Rank, Integer> result;

    public ResultMap() {
        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        this.result = result;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
