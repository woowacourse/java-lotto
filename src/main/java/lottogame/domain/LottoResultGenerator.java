package lottogame.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResultGenerator {
    private static Map<Rank, Integer> result = new LinkedHashMap<>();

    static {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    public static LottoResult create(LottoTickets lottoTickets, WinningLotto winningLotto) {
        List<Rank> matchResults = lottoTickets.getMatchResultEachLottos(winningLotto);
        for (Rank rank : matchResults) {
            result.put(rank, result.get(rank) + 1);
        }
        return new LottoResult(result);
    }
}
