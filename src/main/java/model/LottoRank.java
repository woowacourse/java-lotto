package model;

import exception.InvalidMatchCountException;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(BigDecimal.valueOf(2_000_000_000), (matchCount, bonusMatch) -> matchCount == 6),
    SECOND(BigDecimal.valueOf(30_000_000), (matchCount, bonusMatch) -> matchCount == 5 && bonusMatch),
    THIRD(BigDecimal.valueOf(1_500_000), (matchCount, bonusMatch) -> matchCount == 5 && !bonusMatch),
    FOURTH(BigDecimal.valueOf(50_000), (matchCount, bonusMatch) -> matchCount == 4),
    FIFTH(BigDecimal.valueOf(5_000), (matchCount, bonusMatch) -> matchCount == 3),
    NOTHING(BigDecimal.ZERO, (matchCount, bonusMatch) -> 0 <= matchCount && matchCount < 3);

    private final BigDecimal prize;
    private final BiFunction<Integer, Boolean, Boolean> matchCriteria;

    LottoRank(BigDecimal prize, BiFunction<Integer, Boolean, Boolean> matchCriteria) {
        this.prize = prize;
        this.matchCriteria = matchCriteria;
    }

    public static LottoRank of(Integer matchCount, boolean bonusMatch) {
        return Stream.of(values())
                .filter(rank -> rank.matchCriteria.apply(matchCount, bonusMatch))
                .findFirst()
                .orElseThrow(InvalidMatchCountException::new);
    }

    public BigDecimal multiplePrizeBy(int multipleAmount) {
        return prize.multiply(BigDecimal.valueOf(multipleAmount));
    }
}