package lotto.model;

import java.util.function.BiFunction;
import java.util.stream.Stream;
import lotto.exception.InvalidRankException;

public enum Rank {
    FIRST(new Money(2_000_000_000), Rank::isFirstPrize),
    SECOND(new Money(30_000_000), Rank::isSecondPrize),
    THIRD(new Money(1_500_000), Rank::isThirdPrize),
    FOURTH(new Money(50_000), Rank::isFourthPrize),
    FIFTH(new Money(5_000), Rank::isFifthPrize),
    NOTHING(Money.ZERO, Rank::isNothingPrize);

    private final Money prize;
    private final BiFunction<Integer, Boolean, Boolean> predicate;

    Rank(Money prize, BiFunction<Integer, Boolean, Boolean> predicate) {
        this.prize = prize;
        this.predicate = predicate;
    }

    public Money getPrize() {
        return prize;
    }

    private boolean isMatched(int matchCount, boolean bonusMatch) {
        return predicate.apply(matchCount, bonusMatch);
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        return Stream.of(values())
            .filter(rank -> rank.isMatched(matchCount, bonusMatch))
            .findFirst()
            .orElseThrow(InvalidRankException::new);
    }

    private static boolean isFirstPrize(int matchCount, boolean bonusMatch) {
        return matchCount == 6;
    }

    private static boolean isSecondPrize(int matchCount, boolean bonusMatch) {
        return matchCount == 5 && bonusMatch;
    }

    private static boolean isThirdPrize(int matchCount, boolean bonusMatch) {
        return matchCount == 5 && !bonusMatch;
    }

    private static boolean isFourthPrize(int matchCount, boolean bonusMatch) {
        return matchCount == 4;
    }

    private static boolean isFifthPrize(int matchCount, boolean bonusMatch) {
        return matchCount == 3;
    }

    private static boolean isNothingPrize(int matchCount, boolean bonusMatch) {
        return 0 <= matchCount && matchCount < 3;
    }
}
