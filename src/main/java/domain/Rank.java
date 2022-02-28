package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    NOTHING(0, 0, (matchCounts, bonus) -> matchCounts >= 0 && matchCounts < 3),
    FIFTH(5_000, 3, (matchCounts, bonus) -> matchCounts == 3),
    FOURTH(50_000, 4, (matchCounts, bonus) -> matchCounts == 4),
    THIRD(150_000, 5, (matchCounts, bonus) -> matchCounts == 5 && !bonus),
    SECOND(30_000_000, 5, (matchCounts, bonus) -> matchCounts == 5 && bonus),
    FIRST(2_000_000_000, 6, (matchCounts, bonus) -> matchCounts == 6 && !bonus);

    private final int money;
    private final int matchCounts;
    private final BiPredicate<Integer, Boolean> rankPredicate;

    Rank(int money, int matchCounts, BiPredicate<Integer, Boolean> rankPredicate) {
        this.money = money;
        this.matchCounts = matchCounts;
        this.rankPredicate = rankPredicate;
    }

    public static Rank getRank(int matchCounts, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.rankPredicate.test(matchCounts, bonus))
                .filter(rank -> !rank.isNothing())
                .findFirst()
                .orElse(NOTHING);
    }

    public boolean isNothing() {
        return this == Rank.NOTHING;
    }

    public boolean isSecond() {
        return this == Rank.SECOND;
    }
    
    public int getMoney() {
        return money;
    }

    public int getMatchCounts() {
        return matchCounts;
    }
}
