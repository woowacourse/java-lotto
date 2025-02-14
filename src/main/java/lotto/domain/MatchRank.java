package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_MATCH_NUMBER_NOT_VALID;

public enum MatchRank {
    MATCH_THREE(3, 5000),
    MATCH_FOUR(4, 50000),
    MATCH_FIVE(5, 150000),
    MATCH_BONUS(5, 30000000),
    MATCH_SIX(6, 2000000000),
    NO_MATCH(0, 0);

    private static final int MIN_MATCH_NUMBER = 0;
    private static final int MAX_MATCH_NUMBER = 6;
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

    public static MatchRank getMatchRank(int matchNumber, boolean bonus) {
        validateMatchNumber(matchNumber);

        if (isMatchBonus(matchNumber, bonus)) {
            return MATCH_BONUS;
        }

        for (MatchRank statistics : values()) {
            if (statistics.number == matchNumber) {
                return statistics;
            }
        }

        return NO_MATCH;
    }

    private static boolean isMatchBonus(int matchNumber, boolean bonus) {
        return matchNumber == MATCH_BONUS.number && bonus;
    }

    private static void validateMatchNumber(int matchNumber) {
        if(matchNumber < MIN_MATCH_NUMBER || matchNumber > MAX_MATCH_NUMBER) {
            throw new IllegalArgumentException(ERROR_MATCH_NUMBER_NOT_VALID);
        }
    }
}
