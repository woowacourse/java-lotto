package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class Result {
    private final Map<Rank, Integer> result;

    public Result(Map<Rank, Integer> result) {
        if (Objects.isNull(result) || result.isEmpty()) {
            throw new NullPointerException();
        }
        this.result = result;
    }

    public int get(Rank rank) {
        if (!result.containsKey(rank)) {
            return 0;
        }
        return result.get(rank);
    }
}
