package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LottoResult {
    private static final int PERSENT = 100;

    private Map<Rank, Integer> result = new LinkedHashMap<>();

    private LottoTicket lottoTicket;

    public LottoResult(LottoTicket lottoTicket, WinningLotto winningLotto) {
        this.lottoTicket = lottoTicket;
        initRankResult();
        lottoTicket.matchLotto(winningLotto)
                .stream()
                .forEach(lotto -> putRank(lotto));
    }

    private void initRankResult() {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    private void putRank(Rank rank) {
        result.put(rank, result.get(rank) + 1);
    }

    public Set<Rank> getResultKey() {
        return result.keySet();
    }

    public int getResultValue(Rank result) {
        return this.result.get(result);
    }

    public double dividendRate() {
        double rateResult = 0;
        for (Rank rank : result.keySet()) {
            rateResult += rank.getWinningMoney() * result.get(rank);
        }

        return rateResult / lottoTicket.getPrice() * PERSENT;
    }
}
