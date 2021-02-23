package domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final int correctCount;
    private final Money prize;

    LottoRank(int correctCount, long prize) {
        this.correctCount = correctCount;
        this.prize = new Money(prize);
    }

    public static LottoRank valueOf(WinningLotto winningLotto, Lotto target) {
        return getMatchRank(winningLotto.findMatchCount(target), winningLotto.isBonusBallMatch(target));
    }

    private static LottoRank getMatchRank(int matchCount, boolean isBonusMatch) {
        if (SECOND.isMatch(matchCount) || THIRD.isMatch(matchCount)) {
            return determineSecondOrThird(isBonusMatch);
        }

        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.isMatch(matchCount))
                .findAny()
                .orElse(MISS);
    }

    private static LottoRank determineSecondOrThird(boolean isBonusMatch) {
        if (isBonusMatch) {
            return SECOND;
        }

        return THIRD;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    private boolean isMatch(int countOfMatch) {
        return this.correctCount == countOfMatch;
    }

    public Money getPrize() {
        return prize;
    }

    public Money getPrize(long count) {
        return prize.multiply(count);
    }
}
