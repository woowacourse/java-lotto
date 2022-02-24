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

    private final Map<Rank, Integer> board = new HashMap<>();

    public RankBoard(WinningLotto winningLotto, List<Lotto> tickets) {
        initResult();
        countRank(winningLotto, tickets);
    }

    private void initResult() {
        board.put(Rank.FIRST, INIT_NUMBER);
        board.put(Rank.SECOND, INIT_NUMBER);
        board.put(Rank.THIRD, INIT_NUMBER);
        board.put(Rank.FOURTH, INIT_NUMBER);
        board.put(Rank.FIFTH, INIT_NUMBER);
    }

    private void countRank(WinningLotto winningLotto, List<Lotto> tickets) {
        Set<LottoNumber> winningAndBonusNumber = winningLotto.getWinningAndBonusNumber();
        for (Lotto ticket : tickets) {
            calcRankForEach(ticket.getMatchedNumbers(winningAndBonusNumber), winningLotto);
        }
    }

    private void calcRankForEach(Set<LottoNumber> matchedNumbers, WinningLotto winningLotto) {
        if (isNotRanked(matchedNumbers)) {
            return;
        }
        if (isSecondRank(matchedNumbers, winningLotto)) {
            putResult(SECOND_RANK_SIZE, true);
            return;
        }
        putResult(matchedNumbers.size(), false);
    }

    private boolean isNotRanked(Set<LottoNumber> matchedNumbers) {
        return matchedNumbers.size() < MINIMUM_RANK_SIZE;
    }

    private boolean isSecondRank(Set<LottoNumber> matchedNumbers, WinningLotto winningLotto) {
        return matchedNumbers.size() == SECOND_RANK_MATCHED && matchedNumbers.contains(winningLotto.getBonusNumber());
    }

    private void putResult(int size, boolean isBonusBallMatched) {
        Rank rank = Rank.of(size, isBonusBallMatched);
        board.put(rank, board.get(rank) + 1);
    }

    public double calcProfitRatio(int amount) {
        return Math.round((double) calcProfit() / amount * 100) / 100.0;
    }

    private int calcProfit() {
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
