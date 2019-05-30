package model;

import java.util.Optional;
import java.util.stream.Stream;

public enum LottoRank {
    FIFTH(3, new Money(5000)),
    FOURTH(4, new Money(50000)),
    THIRD(5, new Money(1500000)),
    SECOND(5, new Money(30000000)),
    FIRST(6, new Money(2000000000));

    private final int numberOfMatches;
    private final Money prize;

    LottoRank(int numberOfMatches, Money prize) {
        this.numberOfMatches = numberOfMatches;
        this.prize = prize;
    }

    public static Optional<LottoRank> get(int numberOfMatches, boolean containsBonusNumber) {
        if (numberOfMatches == SECOND.numberOfMatches && containsBonusNumber) {
            return Optional.of(SECOND);
        }
        return Stream.of(LottoRank.values())
                .filter(rank -> rank.numberOfMatches == numberOfMatches)
                .findFirst();
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public Money getPrize() {
        return prize;
    }
}