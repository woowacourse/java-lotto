package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public enum Rank {
    LOSE(-1, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private int matchedCount;
    private Money winningMoney;

    Rank(int matchedCount, int winningMoney) {
        this.matchedCount = matchedCount;
        this.winningMoney = Money.of(winningMoney);
    }

    public static Rank of(int count) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchedCount == count)
                .findFirst()
                .orElse(LOSE);
    }

    public boolean isValidRank() {
        return this != LOSE;
    }

    public static List<Rank> validValues() {
        return Arrays.stream(values())
                .filter(Rank::isValidRank)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public int getContainingCount(List<Rank> ranks) {
        return (int) ranks.stream()
                .filter(rank -> rank.equals(this))
                .count();
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public Money getWinningMoney() {
        return winningMoney;
    }
}