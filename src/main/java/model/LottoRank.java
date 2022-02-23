package model;

import exception.InvalidMatchCountException;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(new Prize(2_000_000_000), LottoRank::isFirstPrize),
    SECOND(new Prize(30_000_000), LottoRank::isSecondPrize),
    THIRD(new Prize(1_500_000), LottoRank::isThirdPrize),
    FOURTH(new Prize(50_000), LottoRank::isFourthPrize),
    FIFTH(new Prize(5_000), LottoRank::isFifthPrize),
    NOTHING(Prize.ZERO, LottoRank::isNothingPrize);

    private final Prize prize;
    private final BiFunction<Integer, Boolean, Boolean> predicate;

    LottoRank(Prize prize, BiFunction<Integer, Boolean, Boolean> predicate) {
        this.prize = prize;
        this.predicate = predicate;
    }

    public Prize getPrize() {
        return prize;
    }

    private boolean isMatched(int matchCount, boolean bonusMatch) {
        return predicate.apply(matchCount, bonusMatch);
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
}
