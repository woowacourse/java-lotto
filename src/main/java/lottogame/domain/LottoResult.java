package lottogame.domain;

import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> result;

    LottoResult(Map<Rank, Integer> result) {
        this.result = result;
        result.remove(Rank.MISS);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : result.keySet()) {
            stringBuilder.append(rank).append(result.get(rank)).append("개\n");
        }
        return stringBuilder.toString();
    }

    private long getRateOfLotto() {
        return 1;
    }
}
