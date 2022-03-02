package model;

import exception.InvalidMatchCountException;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(new Budget(2_000_000_000), (matchCount, bonusMatch) -> matchCount == 6),
    SECOND(new Budget(30_000_000), (matchCount, bonusMatch) -> matchCount == 5 && bonusMatch),
    THIRD(new Budget(1_500_000), (matchCount, bonusMatch) -> matchCount == 5 && !bonusMatch),
    FOURTH(new Budget(50_000), (matchCount, bonusMatch) -> matchCount == 4),
    FIFTH(new Budget(5_000), (matchCount, bonusMatch) -> matchCount == 3),
    NOTHING(Budget.ZERO, (matchCount, bonusMatch) -> 0 <= matchCount && matchCount < 3);

    private final Budget prize;
    private final BiFunction<Integer, Boolean, Boolean> matchCriteria;

    LottoRank(Budget prize, BiFunction<Integer, Boolean, Boolean> matchCriteria) {
        this.prize = prize;
        this.matchCriteria = matchCriteria;
    }

    public static LottoRank of(Integer matchCount, boolean bonusMatch) {
        return Stream.of(values())
                .filter(rank -> rank.matchCriteria.apply(matchCount, bonusMatch))
                .findFirst()
                .orElseThrow(InvalidMatchCountException::new);
    }

    public Budget multiplePrizeBy(int multipleAmount) {
        return prize.multiply(multipleAmount);
    }
}