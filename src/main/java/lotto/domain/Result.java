package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class Result {
    private final Map<Rank, Integer> lottoScore;

    public Result(Map<Rank, Integer> lottoScore) {
        if (Objects.isNull(lottoScore) || lottoScore.isEmpty()) {
            throw new NullPointerException();
        }
        this.lottoScore = lottoScore;
    }

    public int get(Rank rank) {
        if (!lottoScore.containsKey(rank)) {
            return 0;
        }
        return lottoScore.get(rank);
    }

    public double calculateEarningsRate(Payment payment) {
        if (Objects.isNull(payment)) {
            throw new NullPointerException();
        }

        long totalWinningMoney = Rank.calculateTotalWinningMoney(lottoScore);
        return payment.calculateEarningsRate(totalWinningMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(lottoScore, result.lottoScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoScore);
    }
}
