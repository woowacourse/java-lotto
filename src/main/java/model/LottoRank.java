package model;

import java.util.Optional;
import java.util.stream.Stream;

public enum LottoRank {
    FIFTH(3, new Money(5_000)),
    FOURTH(4, new Money(50_000)),
    THIRD(5, new Money(1_500_000)),
    SECOND(5, new Money(30_000_000)),
    FIRST(6, new Money(2_000_000_000));

    private final int numberOfMatches;
    private final Money prize;

    LottoRank(int numberOfMatches, Money prize) {
        this.numberOfMatches = numberOfMatches;
        this.prize = prize;
    }

    public static Optional<LottoRank> valueOf(int numberOfMatches, boolean containsBonusNumber) {
        if ((numberOfMatches == SECOND.numberOfMatches) && containsBonusNumber) {
            return Optional.of(SECOND);
        }
        return Stream.of(LottoRank.values())
                .filter(rank -> rank.numberOfMatches == numberOfMatches)
                .findAny();
    }

    public int numberOfMatches() {
        return this.numberOfMatches;
    }

    public Money prize() {
        return this.prize;
    }
}