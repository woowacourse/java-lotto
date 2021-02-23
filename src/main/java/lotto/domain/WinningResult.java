package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum WinningResult {
    FAILED(0, 0, ""),
    FIFTH_PRIZE(3, 5_000, "3개 일치"),
    FOURTH_PRIZE(4, 50_000, "4개 일치"),
    THIRD_PRIZE(5, 1_500_000, "5개 일치"),
    SECOND_PRIZE(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    FIRST_PRIZE(6, 2_000_000_000, "6개 일치");

    private final int hitCount;
    private final int winnings;
    private final String message;

    WinningResult(int hitCount, int winnings, String message) {
        this.hitCount = hitCount;
        this.winnings = winnings;
        this.message = message;
    }

    public static WinningResult findWinningResult(int matchCount, boolean isBonus) {
        List<WinningResult> lottoResults = Arrays.asList(WinningResult.values());
        WinningResult lottoResult = lottoResults.stream()
                .filter(result -> result.hitCount == matchCount)
                .findFirst()
                .orElse(FAILED);

        if (lottoResult == THIRD_PRIZE && isBonus) {
            return SECOND_PRIZE;
        }
        return lottoResult;
    }

    public String getMessage() {
        return message;
    }

    public int getWinnings() {
        return winnings;
    }

    public int getHitCount() {
        return hitCount;
    }
}
