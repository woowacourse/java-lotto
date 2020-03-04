package domain;

import java.util.Arrays;
import java.util.List;

public enum LottoRank {
    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    FAILED(0, 0);

    private final int prize;
    private final int matchCount;

    LottoRank(int prize, int matchCount) {
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public static LottoRank findLottoRank(int matchCount, boolean isBonus) {
        List<LottoRank> lottoRanks = Arrays.asList(LottoRank.values());
        LottoRank lottoRank = lottoRanks.stream()
                .filter(result -> result.matchCount == matchCount)
                .findFirst()
                .orElse(FAILED);

        if (lottoRank == SECOND && !isBonus) {
            return THIRD;
        }
        return lottoRank;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}