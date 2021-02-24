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

    public Result(LottoTickets lottoTickets, WinningLotto winningLotto) {
        this.results = makeResult(lottoTickets, winningLotto);
    }

    public BigDecimal findEarningsRate(BettingMoney bettingMoney) {
        int prize = results.keySet().stream()
                .mapToInt(this::getTotalPrize)
                .sum();
        return bettingMoney.getEarningRate(prize);
    }

    public Map<LottoRank, Integer> getResults() {
        return new HashMap<>(results);
    }

    private Map<LottoRank, Integer> makeResult(LottoTickets lottoTickets, WinningLotto winningLotto) {
        Map<LottoRank, Integer> copy = new HashMap<>();
        List<LottoRank> lottoRanks = lottoTickets.findLottoRanks(winningLotto);
        lottoRanks.forEach(lottoRank -> {
            copy.computeIfPresent(lottoRank, (key, value) -> value + 1);
            copy.putIfAbsent(lottoRank, 1);
        });
        return copy;
    }

    private int getTotalPrize(LottoRank lottoRank) {
        return lottoRank.getPrize() * results.get(lottoRank);
    }
}
