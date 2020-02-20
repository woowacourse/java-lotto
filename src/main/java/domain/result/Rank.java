package domain.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Rank {

    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private static final Map<Integer, Rank> RANK_MATCHER_WITHOUT_BONUS;

    static {
        RANK_MATCHER_WITHOUT_BONUS = new HashMap<>();

        Arrays.stream(values())
                .filter(rank -> rank.isNot(SECOND))
                .forEach(rank ->
                        RANK_MATCHER_WITHOUT_BONUS.put(rank.countOfMatches, rank));
    }

    private int countOfMatches;
    private int winningMoney;

    Rank(int countOfMatches, int winningMoney) {
        this.countOfMatches = countOfMatches;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int countOfMatches, boolean matchBonusNumber) {
        if (countOfMatches > FIRST.countOfMatches || countOfMatches < MISS.countOfMatches) {
            throw new IllegalArgumentException("당첨 번호 일치 수는 0이상 6이하로만 가능합니다.");
        }
        if (countOfMatches == SECOND.countOfMatches && matchBonusNumber) {
            return SECOND;
        }
        if (countOfMatches < FIFTH.countOfMatches) {
            return RANK_MATCHER_WITHOUT_BONUS.get(MISS.countOfMatches);
        }

        return RANK_MATCHER_WITHOUT_BONUS.get(countOfMatches);
    }

    public boolean isNot(Rank rank) {
        return this != rank;
    }

    public int getCountOfMatches() {
        return countOfMatches;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
