package lotto.domain.result.win.prize;

import lotto.domain.result.LottoResult;
import lotto.view.dto.StatisticsDTO;

import java.util.Arrays;
import java.util.List;

public enum PrizeGroup {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    SIXTH(0, 0);

    private final int matchCount;
    private final int defaultPrize;

    PrizeGroup(int matchCount, int defaultPrize) {
        this.matchCount = matchCount;
        this.defaultPrize = defaultPrize;
    }

    public static PrizeGroup findPrizeByLottoResult(LottoResult result) {
        PrizeGroup prize = Arrays.stream(PrizeGroup.values())
                .filter(aPrize -> result.isEqualToMatchCount(aPrize.matchCount))
                .findFirst()
                .orElse(SIXTH);

        if (prize.isThird(result)) {
            return THIRD;
        }
        return prize;
    }

    private boolean isThird(LottoResult lottoResult) {
        return this == SECOND && !lottoResult.isBonusMatch();
    }

    public static StatisticsDTO toDtos(List<PrizeGroup> prizeGroups) {
        return new StatisticsDTO(prizeGroups);
    }

    public int getDefaultPrize() {
        return defaultPrize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
