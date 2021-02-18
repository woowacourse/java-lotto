package lotto.domain.lotto;

import static java.util.Arrays.stream;

import java.util.function.Function;
import lotto.domain.rank.Ranking;

public enum Rank {
    FIRST(1, 6, false, 2000000000),
    SECOND(2, 5, true, 30000000),
    THIRD(3, 5, false, 1500000),
    FOURTH(4, 4, false, 50000),
    FIFTH(5, 3, false, 5000),
    FAIL(-1,-1,false, 0);

    private final int rank;
    private final int matchedNumber;
    private final boolean hasBonusNumber;
    private final int winnings;
    private final Function<Long, Ranking> ranking;

    Rank(int rank, int matchedNumber, boolean hasBonusNumber, int winnings) {
        this.rank = rank;
        this.matchedNumber = matchedNumber;
        this.hasBonusNumber = hasBonusNumber;
        this.winnings = winnings;
        this.ranking = count -> new Ranking(rank, winnings, count, matchedNumber, hasBonusNumber);
    }

    public int getRank() {
        return rank;
    }

    public static Ranking createRanking(int rank, Long count) {
        return stream(Rank.values())
            .filter(r -> r.rank == rank)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("매칭되는 등수가 없습니다.1"))
            .ranking.apply(count);
    }

    public static Rank getRank(int matchedNumber, boolean hasBonusNumber) {
        return stream(Rank.values())
            .filter(rank -> (rank.matchedNumber == matchedNumber
                && (!rank.hasBonusNumber || hasBonusNumber)))
            .findAny()
            .orElse(Rank.FAIL);
    }
}