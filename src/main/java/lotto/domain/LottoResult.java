package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private static final int LOTTO_PRICE = 1000;
    private static final int PERCENT_CONVERSION = 100;

    private final Map<Rank, Integer> result;
    private int totalLottos = 0;

    public LottoResult() {
        result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    public LottoResult(final Map<Rank, Integer> result) {
        this.result = new HashMap<>(result);
        for (int count : result.values()) {
            totalLottos += count;
        }
    }

    public void add(Rank rank) {
        totalLottos++;
        result.put(rank, result.get(rank) + 1);
    }

    public double calculateYield() {
        double yield = 0;
        for (Rank rank : result.keySet()) {
            yield += rank.getWinningMoney() * ((result.get(rank)) / (double) totalLottos) / LOTTO_PRICE;
        }
        return yield * PERCENT_CONVERSION;
    }

    public StringBuilder getResultMessage() {
        StringBuilder message = new StringBuilder();
        for (Rank rank : Rank.winningValues()) {
            message.append(rank.getCountOfMatch())
                    .append("개 일치 (")
                    .append(rank.getWinningMoney())
                    .append("원)- ")
                    .append(result.get(rank))
                    .append("개\n");
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return totalLottos == that.totalLottos &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, totalLottos);
    }
}
