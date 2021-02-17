package lotto.domain.stats;

import lotto.domain.Rank;

public class LottoResult {
    private Rank rank;

    public LottoResult(int count, boolean bonus) {
        rank = Rank.of(count, bonus);
    }

    public boolean equals(Rank rank) {
        return rank.equals(this.rank);
    }
}
