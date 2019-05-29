package lottogame.domain;

import java.util.Map;

public class LottoResult {
    private static final int PERCENTAGE = 100;
    private Map<Rank, Integer> result;

    LottoResult(Map<Rank, Integer> result) {
        this.result = result;
        result.remove(Rank.MISS);
    }

    public long getRateOfLotto(int price) {
        long profits = 0;

        for (Rank rank : result.keySet()) {
            profits += rank.sumPrizeOf(result.get(rank));
        }

        return (long) (((double) profits / price) * PERCENTAGE);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : result.keySet()) {
            stringBuilder.append(rank).append(result.get(rank)).append("개\n");
        }
        return stringBuilder.toString();
    }
}
