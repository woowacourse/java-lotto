package model;

import exception.InvalidMatchCountException;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(new Money(2_000_000_000), (matchCount, bonusMatch) -> matchCount == 6),
    SECOND(new Money(30_000_000), (matchCount, bonusMatch) -> matchCount == 5 && bonusMatch),
    THIRD(new Money(1_500_000), (matchCount, bonusMatch) -> matchCount == 5 && !bonusMatch),
    FOURTH(new Money(50_000), (matchCount, bonusMatch) -> matchCount == 4),
    FIFTH(new Money(5_000), (matchCount, bonusMatch) -> matchCount == 3),
    NOTHING(Money.ZERO, (matchCount, bonusMatch) -> 0 <= matchCount && matchCount < 3);

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

    public Money getPrize() {
        return prize;
    }

    private boolean isMatched(int matchCount, boolean bonusMatch) {
        return predicate.apply(matchCount, bonusMatch);
    }
}
