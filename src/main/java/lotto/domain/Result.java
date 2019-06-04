package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Result {
    private final Map<Rank, Integer> lottoScore;

    public Result(WinningLotto winningLotto, LottoTickets lottoTickets) {
        if (Objects.isNull(winningLotto) || Objects.isNull(lottoTickets)) {
            throw new NullPointerException();
        }
        lottoScore = new HashMap<>();
        produceMatchResult(winningLotto, lottoTickets);
    }

    private void produceMatchResult(WinningLotto winningLotto, LottoTickets lottoTickets) {
        List<Lotto> tickets = lottoTickets.getLottoTickets();

        for (Lotto ticket : tickets) {
            calculateCountOfMatch(winningLotto, ticket);
        }
    }

    private void calculateCountOfMatch(WinningLotto winningLotto, Lotto ticket) {
        Rank rank = Rank.valueOf(winningLotto.match(ticket), winningLotto.contains(ticket));

        if (lottoScore.containsKey(rank)) {
            lottoScore.put(rank, lottoScore.get(rank) + 1);
            return;
        }

        lottoScore.put(rank, 1);
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
