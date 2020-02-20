package domain;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public enum LottoResult {
    NO_WIN(-1, -1, false),
    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, false),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, true),
    FIFTH(5000, 3, false);

    private int prize;
    private int matchingNumbers;
    private boolean isBonus;

    LottoResult(int prize, int matchingNumbers, boolean isBonus) {
        this.prize = prize;
        this.matchingNumbers = matchingNumbers;
        this.isBonus = isBonus;
    }

    public static LottoResult findLottoResult(int matchingNumbers, boolean isBonus) {
        List<LottoResult> lottoResults = Arrays.asList(LottoResult.values());
        LottoResult lottoResult = lottoResults.stream()
                .filter(result -> result.matchingNumbers == matchingNumbers)
                .findFirst()
                .orElse(NO_WIN);

        if (lottoResult == SECOND && !isBonus) {
            lottoResult = THIRD;
        }
        return lottoResult;
    }

    public double getPrize() {
        return prize;
    }

    public String toString() {
        if (this == NO_WIN) return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.matchingNumbers);
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