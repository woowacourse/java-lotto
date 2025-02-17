package lotto.domain;

public enum MatchRank {
    MATCH_THREE(3, 5000),
    MATCH_FOUR(4, 50000),
    MATCH_FIVE(5, 150000),
    MATCH_BONUS(5, 30000000),
    MATCH_SIX(6, 2000000000),
    NO_MATCH(0, 0);

    private final int number;
    private final int money;

    MatchRank(int number, int money) {
        this.number = number;
        this.money = money;
    }

    public static MatchRank getMatchRank(MatchResult matchResult) {
        int matchCount = matchResult.matchCount();

        if (isMatchBonus(matchResult)) {
            return MATCH_BONUS;
        }

        for (MatchRank rank : values()) {
            if (rank.number == matchCount) {
                return rank;
            }
        }

        return NO_MATCH;
    }

    private static boolean isMatchBonus(MatchResult matchResult) {
        return matchResult.matchCount() == MATCH_BONUS.number && matchResult.isBonusMatched();
    }

    public int getNumber() {
        return number;
    }

    public int getMoney() {
        return money;
    }

}
