package lotto.domain;

import java.util.Arrays;

public enum Ranking {
    FIRST(2000000000, 6),
    SECOND(30000000, 5),
    THIRD(1500000, 5),
    FOURTH(50000, 4),
    FIFTH(5000, 3);

    private final int prize;
    private final int count;

    Ranking(int prize, int count) {
        this.prize = prize;
        this.count = count;
    }

    public static Ranking findRankingByCnt(int cnt) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> ranking.count == cnt)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
