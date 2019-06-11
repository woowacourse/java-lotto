package lottogame.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> result = new LinkedHashMap<>();

    private LottoResult(List<Rank> matchResults) {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        for (Rank rank : matchResults) {
            result.put(rank, result.get(rank) + 1);
        }
        result.remove(Rank.MISS);
    }

    public static LottoResult generate(LottoTickets lottoTickets, WinningLotto winningLotto) {
        List<Rank> matchResults = lottoTickets.getMatchResultEachLotto(winningLotto);

        LottoResult lottoResult = new LottoResult(matchResults);

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
