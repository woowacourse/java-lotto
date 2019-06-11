package lotto.model;

public enum Rank {
    MISS(0, 0), // 낙첨
    FIFTH(3, 5_000), // 5등
    FOURTH(4, 50_000), // 4등
    THIRD(5, 1_500_000), // 3등
    SECOND(5, 30_000_000, ", 보너스 볼 일치"), // 2등
    FIRST(6, 2_000_000_000); // 1등;

    private static final int WINNING_MIN_COUNT = 3;
    private static final String NOT_VALID_VALUE = "는 유효하지 않은 값입니다.";
    private static final String FAILED = "낙첨";
    private static final String MATCHED = "개 일치";
    private static final String CURRENCY_UNIT = "원";
    private static final String PAREN_OPEN = "(";
    private static final String PAREN_CLOSE = ")";
    private static final String SPACE = " ";

    private int countOfMatch;
    private int winningMoney;
    private String additionalMessage = "";

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    Rank(int countOfMatch, int winningMoney, String additionalMessage) {
        this(countOfMatch, winningMoney);
        this.additionalMessage = additionalMessage;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(countOfMatch) && matchBonus) {
            return SECOND;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(countOfMatch)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(countOfMatch + NOT_VALID_VALUE);
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public String getRankDescription() {
        if (MISS.equals(this)) {
            return FAILED;
        }
        return this.countOfMatch
                + MATCHED
                + this.additionalMessage
                + SPACE
                + PAREN_OPEN
                + this.winningMoney
                + CURRENCY_UNIT
                + PAREN_CLOSE;
    }
}
