package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {

    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    FAIL(0, 0, false);

    private final int prize;
    private final int matched;
    private final boolean bonus;

    Rank(final int prize, final int matched, final boolean bonus) {
        this.prize = prize;
        this.matched = matched;
        this.bonus = bonus;
    }

    public static Rank getWinnerPrizeByMatched(final int matched, final boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matched == matched)
                .filter(rank -> rank.bonus == bonus)
                .findAny()
                .orElse(FAIL);
    }

    public static List<Rank> getWinnerRanks() {
        return Arrays.stream(Rank.values())
                .sequential()
                .collect(Collectors.toList());
    }

    public static List<Rank> getValidPrizes() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.FAIL)
                .sorted(Comparator.comparing(Rank::getPrize))
                .collect(Collectors.toList());
    }

    public int getPrize() {
        return prize;
    }

    public int getMatched() {
        return matched;
    }
}
