package model;

import java.util.stream.Stream;

public enum LottoRank {
    NONE(0, new Money(0)),
    FIFTH(3, new Money(5000)),
    FOURTH(4, new Money(50000)),
    THIRD(5, new Money(1500000)),
    SECOND(5, new Money(30000000)),
    FIRST(6, new Money(2000000000));

    private final int matchingNumbers;
    private final Money prize;

    LottoRank(int matchingNumbers, Money prize) {
        this.matchingNumbers = matchingNumbers;
        this.prize = prize;
    }

    public static LottoRank get(int numberOfMatches, boolean containsBonusNumber) {
        if (numberOfMatches == 5 && containsBonusNumber) {
            return SECOND;
        }
        if (numberOfMatches >= 3) {
            return Stream.of(LottoRank.values())
                    .filter(rank -> rank.matchingNumbers == numberOfMatches)
                    .findFirst()
                    .get();
        }
        return NONE;
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    public Money getPrize() {
        return prize;
    }
}
