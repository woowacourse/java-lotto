package lottogame.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResultGenerator {
    private static Map<Rank, Integer> result = new LinkedHashMap<>();

    public static LottoResult create(LottoTickets lottoTickets, WinningLotto winningLotto) {
        initResult();
        List<Rank> matchResults = lottoTickets.getMatchResultEachLotto(winningLotto);
        for (Rank rank : matchResults) {
            result.put(rank, result.get(rank) + 1);
        }
        return new LottoResult(result);
    }

    private static void initResult() {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }
}
