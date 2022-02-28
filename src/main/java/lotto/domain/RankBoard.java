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

    public RankBoard(WinningLotto winningLotto, List<Lotto> tickets) {
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

    private void countRank(WinningLotto winningLotto, List<Lotto> tickets) {
        Set<LottoNumber> winningAndBonusNumber = winningLotto.getWinningAndBonusNumber();
        for (Lotto ticket : tickets) {
            countRankForEach(ticket.getMatchedNumbers(winningAndBonusNumber), winningLotto);
        }
    }

    private void countRankForEach(Set<LottoNumber> matchedNumbers, WinningLotto winningLotto) {
        if (isNotRanked(matchedNumbers)) {
            return;
        }
        if (isSecondRank(matchedNumbers, winningLotto)) {
            writeBoard(SECOND_RANK_SIZE, true);
            return;
        }
        writeBoard(matchedNumbers.size(), false);
    }

    private boolean isNotRanked(Set<LottoNumber> matchedNumbers) {
        return matchedNumbers.size() < MINIMUM_RANK_SIZE;
    }

    private boolean isSecondRank(Set<LottoNumber> matchedNumbers, WinningLotto winningLotto) {
        return matchedNumbers.size() == SECOND_RANK_MATCHED && matchedNumbers.contains(winningLotto.getBonusNumber());
    }

    private void writeBoard(int size, boolean isBonusBallMatched) {
        Rank rank = Rank.of(size, isBonusBallMatched);
        board.put(rank, board.get(rank) + 1);
    }

    public double calculateProfitRatio(int amount) {
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
        return board;
    }

    public int getCount(Rank rank) {
        return board.get(rank);
    }
}
