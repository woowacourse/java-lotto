package domain.result;

import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final Map<LottoRank, Integer> results;

    public Result(final LottoTickets lottoTickets, final WinningLotto winningLotto) {
        this.results = makeResult(lottoTickets, winningLotto);
    }

    public BigDecimal findEarningsRate(final BettingMoney bettingMoney) {
        long prize = results.keySet().stream()
                .mapToLong(this::getTotalPrize)
                .sum();
        return bettingMoney.getEarningRate(prize);
    }

    public Map<LottoRank, Integer> getResults() {
        return new HashMap<>(results);
    }

    private Map<LottoRank, Integer> makeResult(final LottoTickets lottoTickets, final WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = new HashMap<>();
        List<LottoRank> lottoRanks = lottoTickets.findLottoRanks(winningLotto);
        lottoRanks.forEach(lottoRank -> {
            result.computeIfPresent(lottoRank, (key, value) -> value + 1);
            result.putIfAbsent(lottoRank, 1);
        });
        return result;
    }

    private Long getTotalPrize(final LottoRank lottoRank) {
        return Long.valueOf(lottoRank.getPrize()) * results.get(lottoRank);
    }
}
