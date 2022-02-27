package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Prize {

    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, true),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, false),
    FIFTH(5000, 3, false),
    FAIL(0, 0, false);

    private final int prize;
    private final int matched;
    private final boolean bonus;

    Prize(final int prize, final int matched, final boolean bonus) {
        this.prize = prize;
        this.matched = matched;
        this.bonus = bonus;
    }

    public static Prize getWinnerPrizeByMatched(int matched, boolean bonus) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize.matched == matched)
                .filter(prize -> prize.bonus == bonus)
                .findAny()
                .orElse(FAIL);
    }

    public static List<Prize> getWinnerPrices() {
        return Arrays.stream(Prize.values())
                .sequential()
                .collect(Collectors.toList());
    }

    public int getPrize() {
        return prize;
    }

    public int getMatched() {
        return matched;
    }

}
