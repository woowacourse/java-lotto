package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIFTH(5000, "3개 일치 (5000원)- ", 3, false),
    FOURTH(50000, "4개 일치 (50000원)- ", 4, false),
    THIRD(1500000, "5개 일치 (1500000원)- ", 5, false),
    SECOND(30000000, "5개 일치, 보너스 볼 일치(30000000원) - ", 5, true),
    FIRST(2000000000, "6개 일치 (2000000000원)- ", 6, false),
    NONE(0,"", 0, false);

    private final int money;
    private final String message;
    private final int matchCounts;
    private final boolean matchBonus;

    Rank(int money, String message, int matchCounts, boolean matchBonus) {
        this.money = money;
        this.message = message;
        this.matchCounts = matchCounts;
        this.matchBonus = matchBonus;
    }

    public static Rank matchRank(int matchCounts, boolean matchBonus) {
        if (matchCounts == 5) {
            return checkEqualFive(matchBonus);
        }
        if (matchCounts == 6) {
            return FIRST;
        }
        if (matchCounts == 4) {
            return FOURTH;
        }
        if (matchCounts == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public int calculateTotalProfit(int winningCounts) {
        return winningCounts * this.money;
    }

    public static List<Rank> validRank() {
        return new ArrayList<>(Arrays.asList(Rank.values()).subList(0, 5));
    }

    public String getMessage() {
        return message;
    }

    private static Rank checkEqualFive(boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

}
