package lotto.domain.result;

import java.util.Arrays;

public enum Rank {
    DEFAULT(0, 0, false),
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 1_500_000, false),
    BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    private final int matchingNumbers;
    private final int prize;
    private final boolean bonusMatching;

    Rank(int matchingNumbers, int prize, boolean bonusMatching) {
        this.matchingNumbers = matchingNumbers;
        this.prize = prize;
        this.bonusMatching = bonusMatching;
    }

    public static Rank getRank(int numberOfMatch, boolean isBonus) {
         return Arrays.stream(values())
                .filter(rank -> rank.matchingNumbers == numberOfMatch)
                .filter(rank->rank.checkBonus(isBonus))
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

    public boolean checkBonus(boolean isBonus) {
        if (this == BONUS || this == FIVE) {
            return bonusMatching == isBonus;
        }
        return true;
    }

    public boolean isBonus(){
        if(this==BONUS){
            return true;
        }
        return false;
    }
}
