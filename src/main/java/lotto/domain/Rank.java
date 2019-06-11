package lotto.domain;

import java.util.function.Function;

public enum Rank {
    /**
     * countOfMatch가 같은 객체가 있을시 Bonus.TRUE인 것을 뒤에 배치 해야한다.
     * 이유는 Rank.valueOf() 에서 Rank를 찾아서 return 해주는 것 때문이다.
     * ex)
     * THIRD,
     * SECOND
     * countOfMatch = 5, bonus = true일 경우에 SECOND보다 THRID가 먼저 비교되기 때문이다.
     */
    MISS(0, 0, Bonus.FALSE),
    FIFTH(3, 5_000, Bonus.FALSE),
    FOURTH(4, 50_000, Bonus.FALSE),
    THIRD(5, 1_500_000, Bonus.FALSE),
    SECOND(5, 30_000_000, Bonus.TRUE),
    FIRST(6, 2_000_000_000, Bonus.FALSE);

    private static final int RANK_MIN_COUNT = 3;

    private final int countOfMatch;
    private final int prize;
    private final Bonus bonus;

    Rank(final int countOfMatch, final int prize, final Bonus bonus) {
        this.countOfMatch = countOfMatch;
        this.prize = prize;
        this.bonus = bonus;
    }

    public static Rank valueOf(final int countOfMatch, final boolean bonusNo) {
        if (countOfMatch < RANK_MIN_COUNT) {
            return MISS;
        }

        Rank result = null;
        for (final Rank rank : values()) {
            if (rank.isMatchCount(countOfMatch) && rank.bonus.match(bonusNo)) {
                result = rank;
            }
        }

        return result;
    }

    private boolean isMatchCount(final int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getPrize() {
        return prize;
    }

    enum Bonus {
        TRUE((bonus) -> bonus),
        FALSE((bonus) -> true);

        private Function<Boolean, Boolean> expression;

        Bonus(final Function<Boolean, Boolean> expression) {
            this.expression = expression;
        }

        public boolean match(boolean bonusNo) {
            return expression.apply(bonusNo);
        }
    }
}

