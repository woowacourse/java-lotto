package lotto.domain.result;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Rank {
    DEFAULT(0, 0, false),
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 1_500_000, false),
    BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    public static final int FIVE_MATCH = 5;
    private final int matchingNumbers;
    private final int prize;
    private final boolean bonusMatching;

    Rank(int matchingNumbers, int prize, boolean bonusMatching) {
        this.matchingNumbers = matchingNumbers;
        this.prize = prize;
        this.bonusMatching = bonusMatching;
    }

    public static Rank getRank(int numberOfMatch, boolean isBonus) {
        Stream<Rank> rankStream = Arrays.stream(values())
                .filter(statistic -> statistic.matchingNumbers == numberOfMatch);

        if (numberOfMatch != FIVE_MATCH) {
            return rankStream
                    .findFirst()
                    .orElse(DEFAULT);
        }
        return rankStream
                .filter(statistic -> statistic.bonusMatching == isBonus)
                .findFirst()
                .orElse(DEFAULT);
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isNotDefault() {
        return matchingNumbers != 0;
    }

    public boolean isBonusMatching(){
        return matchingNumbers==FIVE_MATCH && bonusMatching;
    }
}
