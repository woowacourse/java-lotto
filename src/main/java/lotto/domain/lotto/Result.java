package lotto.domain.lotto;

import lotto.domain.paymentinfo.Payment;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Result {
    private final Map<Rank, Long> lottoScore;

    public Result(WinningLotto winningLotto, LottoTickets lottoTickets) {
        this.lottoScore = calculateLottoResult(winningLotto, lottoTickets);
    }

    private Map<Rank, Long> calculateLottoResult(WinningLotto winningLotto, LottoTickets lottoTickets) {
        return lottoTickets.stream()
                .map(winningLotto::match)
                .collect(Collectors.groupingBy(Function.identity(),
                        () -> new EnumMap<>(Rank.class),
                        Collectors.counting()));
    }

    public long get(Rank rank) {
        if (!lottoScore.containsKey(rank)) {
            return 0;
        }
        return lottoScore.get(rank);
    }

    public double calculateEarningsRate(Payment payment) {
        if (Objects.isNull(payment)) {
            throw new NullPointerException();
        }
        return payment.calculateEarningsRate(calculateTotalWinningMoney());
    }

    private long calculateTotalWinningMoney() {
        return lottoScore.keySet().stream()
                .mapToLong(rank -> rank.calculateWinningMoney(lottoScore.get(rank)))
                .sum();
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
