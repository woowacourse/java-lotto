package domain;

import java.util.Arrays;
import java.util.List;

public enum LottoResult {
    FAILED(0, -1),
    FIRST(2000000000, 6),
    SECOND(30000000, 5),
    THIRD(1500000, 5),
    FOURTH(50000, 4),
    FIFTH(5000, 3);

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
            lottoResult = THIRD;
        }
        return lottoResult;
    }

    public int getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        if (this == FAILED) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.matchCount);
        stringBuilder.append("개 일치 ");
        if (this == SECOND) {
            stringBuilder.append(", 보너스 볼 일치 ");
        }
        stringBuilder.append("(");
        stringBuilder.append(this.prize);
        stringBuilder.append("원) - ");

        return stringBuilder.toString();
    }
}