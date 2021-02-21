package domain.result;

import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final LottoTickets lottoTickets;
    private final WinningLotto winningLotto;
    private final Map<LottoRank, Integer> results;

    public Result(LottoTickets lottoTickets, WinningLotto winningLotto) {
        this.lottoTickets = lottoTickets;
        this.winningLotto = winningLotto;
        this.results = new HashMap<>();
        setResult();
    }

    private Map<LottoRank, Integer> setResult() {
        List<LottoRank> lottoRanks = lottoTickets.findMatches(winningLotto);
        lottoRanks.forEach(this::putResult);
        return results;
    }

    public BigDecimal findEarningsRate(BettingMoney bettingMoney) {
        int prize = results.entrySet().stream()
                .map(Map.Entry::getKey)
                .mapToInt(lottoRank -> lottoRank.getPrize() * results.get(lottoRank))
                .sum();
        return bettingMoney.getEarningRate(prize);
    }

    private void putResult(final LottoRank lottoRank) {
        if (!results.containsKey(lottoRank)) {
            results.put(lottoRank, 1);
            return;
        }
        results.put(lottoRank, results.get(lottoRank) + 1);
    }

    public Map<LottoRank, Integer> getResults() {
        return results;
    }
}
