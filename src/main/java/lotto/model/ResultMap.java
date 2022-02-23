package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {
    Map<Rank, Integer> result;

    public ResultMap() {
        this.result = new HashMap<>();
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
