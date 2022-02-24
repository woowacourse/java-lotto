package model;

import exception.InvalidMatchCountException;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(new Money(2_000_000_000), LottoRank::isFirstPrize),
    SECOND(new Money(30_000_000), LottoRank::isSecondPrize),
    THIRD(new Money(1_500_000), LottoRank::isThirdPrize),
    FOURTH(new Money(50_000), LottoRank::isFourthPrize),
    FIFTH(new Money(5_000), LottoRank::isFifthPrize),
    NOTHING(Money.ZERO, LottoRank::isNothingPrize);

    private final Money prize;
    private final BiFunction<Integer, Boolean, Boolean> predicate;

    LottoRank(Money prize, BiFunction<Integer, Boolean, Boolean> predicate) {
        this.prize = prize;
        this.predicate = predicate;
    }

    public static LottoRank of(Integer matchCount, boolean bonusMatch) {
        return Stream.of(values())
                .filter(rank -> rank.isMatched(matchCount, bonusMatch))
                .findFirst()
                .orElseThrow(InvalidMatchCountException::new);
    }

    private static boolean isFirstPrize(Integer matchCount, boolean bonusMatch) {
        return matchCount == 6;
    }

    private static boolean isSecondPrize(Integer matchCount, boolean bonusMatch) {
        return matchCount == 5 && bonusMatch;
    }

    private static boolean isThirdPrize(Integer matchCount, boolean bonusMatch) {
        return matchCount == 5 && !bonusMatch;
    }

    private static boolean isFourthPrize(Integer matchCount, boolean bonusMatch) {
        return matchCount == 4;
    }

    private static boolean isFifthPrize(Integer matchCount, boolean bonusMatch) {
        return matchCount == 3;
    }

    private static boolean isNothingPrize(Integer matchCount, boolean bonusMatch) {
        return 0 <= matchCount && matchCount < 3;
    }

    public Money getPrize() {
        return prize;
    }

    private boolean isMatched(int matchCount, boolean bonusMatch) {
        return predicate.apply(matchCount, bonusMatch);
    }
}
