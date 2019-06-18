package lotto.dto;

import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.RankResult;

/**
 * @author heebg
 * @version 1.0 2019-06-12
 */
public class WinnerDTO {
    private RankResult rankResult;

    public WinnerDTO(RankResult rankResult) {
        this.rankResult = rankResult;
    }

    public float getProfit() {
        return rankResult.getRate();
    }

    public LottoNumber winLotto(int index) {
        return rankResult.winLotto(index);
    }

    public LottoNumber winBonus() {
        return rankResult.winBonus();
    }

    public int matchRankCount(Rank rank) {
        return rankResult.matchRankCount(rank);
    }
}
