package lotto.domain.rank;

import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.function.Function;

public enum RankFactory {
    FIRST(1, 6, false, 2000000000),
    SECOND(2, 5, true, 30000000),
    THIRD(3, 5, false, 1500000),
    FOURTH(4, 4, false, 50000),
    FIFTH(5, 3, false, 5000),
    FAIL(-1, -1, false, 0);

    private final int rank;
    private final int matchedNumber;
    private final boolean hasBonusNumber;
    private final Function<Long, Rank> ranking;

    RankFactory(int rank, int matchedNumber, boolean hasBonusNumber, int winnings) {
        this.rank = rank;
        this.matchedNumber = matchedNumber;
        this.hasBonusNumber = hasBonusNumber;
        this.ranking = count -> new Rank(rank, winnings, count, matchedNumber, hasBonusNumber);
    }

    public boolean isSameRank(int rank) {
        return this.equals(rank);
    }

    public static Rank createRanking(RankFactory rank, Long count) {
        return stream(RankFactory.values())
                .filter(r -> r.equals(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("매칭되는 등수가 없습니다."))
                .ranking.apply(count);
    }

    public static RankFactory getRank(int matchedNumber, boolean hasBonusNumber) {
        return Arrays.stream(RankFactory.values())
                .filter(rankFactory -> rankConditionFilter(rankFactory, matchedNumber, hasBonusNumber))
                .findAny()
                .orElse(RankFactory.FAIL);
    }

    private static boolean rankConditionFilter(RankFactory rankFactory,
                                               int matchedNumber,
                                               boolean hasBonusNumber) {
        return rankFactory.hasBonusNumber && hasBonusNumber && rankFactory.matchedNumber == matchedNumber ||
                !rankFactory.hasBonusNumber && rankFactory.matchedNumber == matchedNumber;
    }
}