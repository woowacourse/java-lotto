package lotto.domain;

import lotto.domain.exception.RankNotExistException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private final int matchCount;
    private final int money;

    Rank(final int matchCount, final int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    static Rank valueOf(final int matchCount, boolean existBonus) {
        if (matchCount < WINNING_MIN_COUNT) {
            return MISS;
        }
        if (existBonus && (matchCount == SECOND.matchCount)) {
            return SECOND;
        }
        return getRankWithoutSecond(matchCount);
    }

    private static Rank getRankWithoutSecond(int matchCount) {
        return Arrays.asList(values())
                .stream()
                .filter(value -> isValueMatch(matchCount, value))
                .findFirst()
                .orElseThrow(() -> new RankNotExistException(matchCount + "에 맞는 등수가 존재하지 않습니다"));
    }

    private static boolean isValueMatch(int matchCount, Rank value) {
        return (value != SECOND) && (value.matchCount == matchCount);
    }

    double prize(final int count) {
        return (this.money * count);
    }

    public static List<Rank> reverseValues() {
        List<Rank> ranks = Arrays.asList(values());
        List<Rank> ranksWithoutMiss = ranks.subList(0, ranks.size() - 1);
        Collections.reverse(ranksWithoutMiss);
        return ranksWithoutMiss;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMoney() {
        return money;
    }
}
