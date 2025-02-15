package lotto.domain;

import static lotto.common.constant.BusinessRule.*;

import java.util.Arrays;

public enum MatchInfo {
    MATCH_THREE(3, 5000),
    MATCH_FOUR(4, 50000),
    MATCH_FIVE(5, 150000),
    MATCH_BONUS(5, 30000000),
    MATCH_SIX(6, 2000000000),
    NO_MATCH(0, 0);

    private final static String BONUS_OUTPUT = "%d개 일치, 보너스 볼 일치 (%d원)- ";
    private final static String BASIC_OUTPUT = "%d개 일치 (%d원)- ";

    private final int number;
    private final int money;

    MatchInfo(int number, int money) {
        this.number = number;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getMatchData() {
        if (this == MATCH_BONUS) {
            return String.format(BONUS_OUTPUT, number, money);
        }
        return String.format(BASIC_OUTPUT, number, money);
    }

    public static MatchInfo getMatchInfo(int matchNumber, boolean bonus) {
        if (isMatchBonus(matchNumber, bonus)) {
            return MATCH_BONUS;
        }

        return Arrays.stream(values())
            .filter(s -> s.number == matchNumber)
            .findFirst()
            .orElse(NO_MATCH);
    }

    private static boolean isMatchBonus(int matchNumber, boolean bonus) {
        return matchNumber == LOTTO_MATCH_COUNT_FOR_BONUS && bonus;
    }
}
