package lotto.domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankBoard {

    private static final int DEFAULT_VALUE = 0;
    private static final int ROUNDING_DIGITS = 100;
    private static final double ROUNDING_DIGITS_DOUBLE = 100.0;

    private final Map<Rank, Integer> board = new EnumMap<>(Rank.class);

    public RankBoard(final WinningLotto winningLotto, final List<Lotto> tickets) {
        countRank(winningLotto, tickets);
    }

    private void countRank(final WinningLotto winningLotto, final List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            writeBoard(winningLotto.countWinningMatched(lotto), winningLotto.isBonusMatched(lotto));
        }
    }

    private void writeBoard(final int matched, final boolean bonusMatched) {
        final Rank rank = Rank.of(matched, bonusMatched);
        board.put(rank, board.getOrDefault(rank, DEFAULT_VALUE) + 1);
    }

    public double calculateProfitRatio(final int amount) {
        return Math.round((double) calculateProfit() / amount * ROUNDING_DIGITS) / ROUNDING_DIGITS_DOUBLE;
    }

    private long calculateProfit() {
        long profit = 0;
        for (Rank rank : board.keySet()) {
            profit += board.getOrDefault(rank, DEFAULT_VALUE) * rank.prize();
        }
        return profit;
    }

    public Map<Rank, Integer> getBoard() {
        return new HashMap<>(board);
    }

    public int getCount(final Rank rank) {
        return board.getOrDefault(rank, DEFAULT_VALUE);
    }
}
