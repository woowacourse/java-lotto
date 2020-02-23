package lotto.domain.result.win.rank;

import lotto.domain.result.LottoResult;
import lotto.view.dto.ResultDTO;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    SIXTH(0, 0);

    private final int matchCount;       // 우승 로또 번호와 일치하는 번호 갯수
    private final int defaultPrize;     // 등수 별 상금

    Rank(int matchCount, int defaultPrize) {
        this.matchCount = matchCount;
        this.defaultPrize = defaultPrize;
    }

    public static Rank findRankByLottoResult(LottoResult result) {
        Rank rank = Arrays.stream(Rank.values())
                .filter(aRank -> result.isEqualToMatchCount(aRank.matchCount))
                .findFirst()
                .orElse(SIXTH);

        if (rank.isThird(result)) {
            return THIRD;
        }
        return rank;
    }

    private boolean isThird(LottoResult lottoResult) {
        return this == SECOND && !lottoResult.isBonusMatch();
    }

    public static ResultDTO toDtos(List<Rank> ranks) {
        return new ResultDTO(ranks);
    }

    public int getDefaultPrize() {
        return defaultPrize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
