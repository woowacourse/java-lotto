package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private static final int ZERO = 0;
    private static final int LOTTO_PRICE = 1000;
    private static final int PERCENT_CONVERSION = 100;

    private final Map<Rank, Integer> result;

    public LottoResult() {
        result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, ZERO);
        }
    }

    public LottoResult(final Map<Rank, Integer> result) {
        this.result = new HashMap<>(result);
    }

    public int getRankCount(Rank rank) {
        return result.get(rank);
    }

    void add(Rank rank) {
        result.put(rank, result.get(rank) + 1);
    }

    public double calculateYield() {
        double yield = ZERO;
        int totalLottos = calculateTotalLottos();
        for (Rank rank : result.keySet()) {
            yield += rank.getWinningMoney() * ((result.get(rank)) / (double) totalLottos);
        }
        yield /= LOTTO_PRICE;
        return yield * PERCENT_CONVERSION;
    }

    private int calculateTotalLottos() {
        return result.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
