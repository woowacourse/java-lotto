package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    MISS(0, 0);

    private final int countOfMatch;
    private final int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int countOfMatch) {
        if (countOfMatch == 1 || countOfMatch == 2) {
            return MISS;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.match(countOfMatch))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 Rank가 없습니다"));
    }

    private boolean match(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    public static long calculateTotalWinningMoney(Map<Rank, Integer> map) {
        if (Objects.isNull(map) || map.isEmpty()) {
            throw new NullPointerException();
        }

        long sum = 0;
        for (Rank rank : map.keySet()) {
            sum += (long) rank.winningMoney * map.get(rank);
        }

        return sum;
    }
}
