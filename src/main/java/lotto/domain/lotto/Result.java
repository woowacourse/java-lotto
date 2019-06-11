package lotto.domain.lotto;

import lotto.domain.paymentinfo.Payment;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Result {
    private final Map<Rank, Long> lottoScore;

    public Result(WinningLotto winningLotto, LottoTickets lottoTickets) {
        this.lottoScore = calculateLottoResult(winningLotto, lottoTickets);
    }

    private Map<Rank, Long> calculateLottoResult(WinningLotto winningLotto, LottoTickets lottoTickets) {
        return lottoTickets.getAllLottoTickets()
                .stream()
                .map(winningLotto::match)
                .collect(groupingBy(identity(),
                        () -> new EnumMap<>(Rank.class),
                        counting()));
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
