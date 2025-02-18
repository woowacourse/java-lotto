package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Wallet {
    private final List<Lotto> lottos;

    public Wallet(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<MatchRank, Integer> getRankCounts(WinningInform winningInform) {
        Map<MatchRank, Integer> matchRankResult = new EnumMap<>(MatchRank.class);
        for (MatchRank rank : MatchRank.values()) {
            matchRankResult.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            MatchRank rank = winningInform.match(lotto);
            matchRankResult.put(rank, matchRankResult.getOrDefault(rank, 0) + 1);
        }

        return matchRankResult;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
