package lotto.domain.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final Map<Integer, Rank> RANK_MATCHER_WITHOUT_BONUS;

    static {
        RANK_MATCHER_WITHOUT_BONUS = new HashMap<>();

        Arrays.stream(values())
                .filter(rank -> rank.isNot(SECOND))
                .filter(rank -> rank.isNot(MISS))
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
        validateCountOfMatches(countOfMatches);
        if (countOfMatches == SECOND.countOfMatches && matchBonusNumber) {
            return SECOND;
        }
        Optional<Rank> rank = Optional.ofNullable(RANK_MATCHER_WITHOUT_BONUS.get(countOfMatches));
        return rank.orElse(MISS);
    }

    private static void validateCountOfMatches(int countOfMatches) {
        if (countOfMatches > FIRST.countOfMatches || countOfMatches < MISS.countOfMatches) {
            throw new IllegalArgumentException("당첨 번호 일치 수는 0이상 6이하로만 가능합니다.");
        }
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
