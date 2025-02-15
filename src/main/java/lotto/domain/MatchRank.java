package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_MATCH_NUMBER_NOT_VALID;

public enum MatchRank {
    MATCH_THREE(3, 5000),
    MATCH_FOUR(4, 50000),
    MATCH_FIVE(5, 150000),
    MATCH_BONUS(5, 30000000),
    MATCH_SIX(6, 2000000000),
    NO_MATCH(0, 0);

    private final static String BONUS_OUTPUT_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원)- ";
    private final static String BASIC_OUTPUT_MESSAGE = "%d개 일치 (%d원)- ";

    private final int number;
    private final int money;

    MatchRank(int number, int money) {
        this.number = number;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getMatchData() {
        if (this == MATCH_BONUS) {
            return String.format(BONUS_OUTPUT_MESSAGE, number, money);
        }
        return String.format(BASIC_OUTPUT_MESSAGE, number, money);
    }

    public static MatchRank getMatchRank(MatchResult matchResult) {
        int matchCount = matchResult.matchCount();

        if (isMatchBonus(matchResult)) {
            return MATCH_BONUS;
        }

        for (MatchRank statistics : values()) {
            if (statistics.number == matchCount) {
                return statistics;
            }
        }

        return NO_MATCH;
    }

    private static boolean isMatchBonus(MatchResult matchResult) {
        return matchResult.matchCount() == MATCH_BONUS.number && matchResult.isBonusMatched();
    }

}
