package domain.result;

import java.util.Arrays;

public enum LottoRank {

    NO_WIN(0, -1, false),
    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, true),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, false),
    FIFTH(5000, 3, false);

    private final int prize;
    private final int matchingNumbers;
    private final boolean hasBonus;

    LottoRank(int prize, int matchingNumbers, boolean hasBonus) {
        this.prize = prize;
        this.matchingNumbers = matchingNumbers;
        this.hasBonus = hasBonus;
    }

    public static LottoRank valueOf(int matchingNumbers, boolean hasBonus) {
        LottoRank lottoRank = Arrays.asList(LottoRank.values()).stream()
                .filter(result -> result.matchingNumbers == matchingNumbers)
                .findFirst()
                .orElse(NO_WIN);

        if (lottoRank == SECOND && !hasBonus) {
            lottoRank = THIRD;
        }

        return lottoRank;
    }

    public int getPrize() {
        return this.prize;
    }

    public int getMatchingNumbers() {
        return this.matchingNumbers;
    }
}