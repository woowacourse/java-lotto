package lotto.domain;

public enum MatchStatistics {
    MATCH_THREE(3, 5000),
    MATCH_FOUR(4, 50000),
    MATCH_FIVE(5, 150000),
    MATCH_BONUS(5, 30000000),
    MATCH_SIX(6, 2000000000),
    NO_MATCH(0, 0);;

    private int number;
    private int money;

    MatchStatistics(int number, int money) {
    }

    public MatchStatistics getMatchStatistics(int matchNumber, boolean bonus) {
        if (matchNumber == 5 && bonus) {
            return MATCH_BONUS;
        }
        //당첨 번호 개수  보너스 번호
        for (MatchStatistics statistics : values()) {
            if (statistics.number == matchNumber) {
                return statistics;
            }
        }
        return NO_MATCH;
    }
}
