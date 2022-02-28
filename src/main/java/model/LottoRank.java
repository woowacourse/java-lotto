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
    private final BiFunction<Integer, Boolean, Boolean> matchCriteria;

    LottoRank(Money prize, BiFunction<Integer, Boolean, Boolean> matchCriteria) {
        this.prize = prize;
        this.matchCriteria = matchCriteria;
    }

    public static LottoRank of(Integer matchCount, boolean bonusMatch) {
        return Stream.of(values())
                .filter(rank -> rank.matchCriteria.apply(matchCount, bonusMatch))
                .findFirst()
                .orElseThrow(InvalidMatchCountException::new);
    }

    public Money getPrize() {
        return prize;
    }
}