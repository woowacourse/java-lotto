package domain;

import java.util.Map;

public class WinningStat {

    private final Map<LottoRank, Integer> stats;

    public WinningStat(Map<LottoRank, Integer> ranks) {
        stats = ranks;
    }

    public Map<LottoRank, Integer> getStat() {
        return stats;
    }
}
