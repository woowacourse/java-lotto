package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RankBoard {

    private static final int MINIMUM_RANK_SIZE = 3;
    private static final int INIT_NUMBER = 0;
    private static final int SECOND_RANK_SIZE = 5;
    private static final int SECOND_RANK_MATCHED = 6;
    private static final int ROUNDING_DIGITS = 100;
    private static final double ROUNDING_DIGITS_DOUBLE = 100.0;

    private final Map<Rank, Integer> board = new HashMap<>();

    public RankBoard(final WinningLotto winningLotto, final List<Lotto> tickets) {
        initBoard();
        countRank(winningLotto, tickets);
    }

    private void initBoard() {
        board.put(Rank.FIRST, INIT_NUMBER);
        board.put(Rank.SECOND, INIT_NUMBER);
        board.put(Rank.THIRD, INIT_NUMBER);
        board.put(Rank.FOURTH, INIT_NUMBER);
        board.put(Rank.FIFTH, INIT_NUMBER);
    }

    private void countRank(final WinningLotto winningLotto, final List<Lotto> tickets) {
        final Set<LottoNumber> winningAndBonusNumber = winningLotto.getWinningAndBonusNumber();
        for (Lotto ticket : tickets) {
            countRankForEach(ticket.getMatchedNumbers(winningAndBonusNumber), winningLotto);
        }
    }

    private void countRankForEach(final Set<LottoNumber> matchedNumbers, final WinningLotto winningLotto) {
        if (isNotRanked(matchedNumbers)) {
            return;
        }
        if (isSecondRank(matchedNumbers, winningLotto)) {
            writeBoard(SECOND_RANK_SIZE, true);
            return;
        }
        writeBoard(matchedNumbers.size(), false);
    }

    private boolean isNotRanked(final Set<LottoNumber> matchedNumbers) {
        return matchedNumbers.size() < MINIMUM_RANK_SIZE;
    }

    private boolean isSecondRank(final Set<LottoNumber> matchedNumbers, final WinningLotto winningLotto) {
        return matchedNumbers.size() == SECOND_RANK_MATCHED && matchedNumbers.contains(winningLotto.getBonusNumber());
    }

    private void writeBoard(final int size, final boolean isBonusBallMatched) {
        final Rank rank = Rank.of(size, isBonusBallMatched);
        board.put(rank, board.get(rank) + 1);
    }

    public double calculateProfitRatio(final int amount) {
        return Math.round((double) calculateProfit() / amount * ROUNDING_DIGITS) / ROUNDING_DIGITS_DOUBLE;
    }

    private int calculateProfit() {
        int profit = 0;
        for (Rank rank : board.keySet()) {
            profit += board.get(rank) * rank.getPrize();
        }
        return profit;
    }

    public Map<Rank, Integer> getBoard() {
        return new HashMap<>(board);
    }

    public int getCount(final Rank rank) {
        return board.get(rank);
    }
}
