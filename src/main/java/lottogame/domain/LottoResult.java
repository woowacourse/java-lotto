package lottogame.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> result = new LinkedHashMap<>();

    LottoResult() {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        result.remove(Rank.MISS);
    }

    public static LottoResult generate(LottoTickets lottoTickets, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        List<Rank> matchResults = lottoTickets.getMatchResultEachLotto(winningLotto);

        for (Rank rank : matchResults) {
            lottoResult.result.put(rank, lottoResult.result.get(rank) + 1);
        }

        return lottoResult;
    }

    public long getRateOfLotto(Money money) {
        double profits = 0;

        for (Rank rank : result.keySet()) {
            profits += rank.sumPrizeOf(result.get(rank));
        }

        return money.rateOf(profits);
    }

    public int getNumberOfMatch(Rank rank) {
        return result.get(rank);
    }
}
