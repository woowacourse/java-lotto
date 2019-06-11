package lotto.domain;

import lotto.exception.InvalidRankMatchException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false);

    private static final int WINNING_MIN_COUNT = 3;
    private static final int FIRST_LIST_INDEX = 0;

    private int countOfMatch;
    private int winningMoney;
    private boolean bonusMatch;

    Rank(int countOfMatch, int winningMoney, boolean bonusMatch) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
        this.bonusMatch = bonusMatch;
    }

    public static Rank valueOf(int countOfMatch, boolean bonusMatch) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        List<Rank> allRank = Arrays.asList(Rank.values());
        List<Rank> matchRank = allRank.stream()
                .filter(rank -> ((rank.countOfMatch == countOfMatch) && (rank.bonusMatch == bonusMatch)))
                .collect(Collectors.toList());

        if (matchRank.size() == 1) {
            return matchRank.get(FIRST_LIST_INDEX);
        }

        throw new InvalidRankMatchException("당첨 RANK 매칭 결과가 없습니다.");
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
