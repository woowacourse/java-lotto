package domain;

import java.util.Arrays;
import java.util.List;

public enum LottoResult {
    FAILED(0, 0),
    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3);

    private final int prize;
    private final int matchCount;

    LottoResult(int prize, int matchCount) {
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public static LottoResult findLottoResult(int matchCount, boolean isBonus) {
        List<LottoResult> lottoResults = Arrays.asList(LottoResult.values());
        LottoResult lottoResult = lottoResults.stream()
                .filter(result -> result.matchCount == matchCount)
                .findFirst()
                .orElse(FAILED);

        if (lottoResult == SECOND && !isBonus) {
            return THIRD;
        }
        return lottoResult;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}