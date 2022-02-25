package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    /*
    DEFAULT = 2개 이하의 matchCount, 기본값으로 지정.
    2등과 3등의 차이는 보너스의 여부에 따르며, 5개가 동일하고 + 보너스 번호가 존재하면 2등으로 구성한다.
    */

    DEFAULT(0, 0),
    FIFTH(5000, 3),
    FOURTH(50000, 4),
    THIRD(1500000, 5),
    SECOND(30000000, 5),
    FIRST(2000000000, 6);

    private final int prizeMoney;
    private final int matchCount;

    Rank(int prizeMoney, int matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public static Rank of(int matchCount, boolean isBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.isMatch(matchCount))
            .filter(rank -> rank.equals(SECOND) || !isBonus)   // 2등과 3등을 구별짓는 로직. (보너스 여부 확인)
            .findFirst()
            .orElse(DEFAULT);
    }

    public static List<Rank> getWithoutDefault() {
        return Arrays.stream(Rank.values())
            .filter(rank -> !rank.equals(DEFAULT))
            .collect(Collectors.toList());
    }

    private boolean isMatch(int matchCount) {
        return this.matchCount == matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
