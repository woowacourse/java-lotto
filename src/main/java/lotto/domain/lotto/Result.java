package lotto.domain.lotto;

import lotto.domain.paymentinfo.Payment;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class Result {
    private final Map<Rank, Long> lottoScore;

    public Result(Map<Rank, Long> lottoScore) {
        this.lottoScore = new EnumMap<>(lottoScore);
    }

    public long get(Rank rank) {
        return lottoScore.getOrDefault(rank, 0L);
    }

    public double calculateEarningsRate(Payment payment) {
        return Optional.of(payment)
                .orElseThrow(NullPointerException::new)
                .calculateEarningsRate(calculateTotalWinningMoney());
    }

    private long calculateTotalWinningMoney() {
        return lottoScore.keySet().stream()
                .mapToLong(rank -> rank.calculateWinningMoney(lottoScore.get(rank)))
                .sum();
    }

    // FIXME: 2019-06-12 Temporary method
    public Map<Rank, Long> getLottoScore() {
        return lottoScore;
    }
}
