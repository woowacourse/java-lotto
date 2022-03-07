package model;

import java.math.BigDecimal;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(BigDecimal.valueOf(2_000_000_000), 6),
    SECOND(BigDecimal.valueOf(30_000_000), 5),
    THIRD(BigDecimal.valueOf(1_500_000), 5),
    FOURTH(BigDecimal.valueOf(50_000), 4),
    FIFTH(BigDecimal.valueOf(5_000), 3),
    NOTHING(BigDecimal.ZERO, 2);

    private final BigDecimal prize;
    private final int matchCount;

    LottoRank(BigDecimal prize, int matchCount) {
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public static LottoRank of(Integer matchCount, boolean bonusMatch) {
        checkMatchCountRange(matchCount);
        if (matchCount == LottoRank.THIRD.matchCount && !bonusMatch) {
            return LottoRank.THIRD;
        }
        return Stream.of(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(LottoRank.NOTHING);
    }

    private static void checkMatchCountRange(Integer matchCount) {
        if (matchCount < 0 || matchCount > 6) {
            throw new IllegalArgumentException("일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.");
        }
    }

    public BigDecimal multiplePrizeBy(int multipleAmount) {
        return prize.multiply(BigDecimal.valueOf(multipleAmount));
    }
}