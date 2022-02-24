package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Result {

    private static final int MINIMUM_RANK_SIZE = 3;
    private static final int INIT_NUMBER = 0;
    private static final int SECOND_RANK_SIZE = 5;
    private static final int SECOND_RANK_MATCHED = 6;

    private final Map<Rank, Integer> rankCounter = new HashMap<>();
    private double profitRatio = 0.0;

    public Result() {
        initResult();
    }

    private void initResult() {
        rankCounter.put(Rank.FIRST, INIT_NUMBER);
        rankCounter.put(Rank.SECOND, INIT_NUMBER);
        rankCounter.put(Rank.THIRD, INIT_NUMBER);
        rankCounter.put(Rank.FOURTH, INIT_NUMBER);
        rankCounter.put(Rank.FIFTH, INIT_NUMBER);
    }

    public void countRank(TotalWinningNumber totalWinningNumber, List<LottoNumbers> tickets) {
        Set<LottoNumber> winningAndBonusNumber = totalWinningNumber.getWinningAndBonusNumber();
        for (LottoNumbers ticket : tickets) {
            calcRankForEach(ticket.getMatchedNumbers(winningAndBonusNumber), totalWinningNumber);
        }
    }

    private void calcRankForEach(Set<LottoNumber> matchedNumbers, TotalWinningNumber totalWinningNumber) {
        if (isNotRanked(matchedNumbers)) {
            return;
        }
        if (isSecondRank(matchedNumbers, totalWinningNumber)) {
            putResult(SECOND_RANK_SIZE, true);
            return;
        }
        putResult(matchedNumbers.size(), false);
    }

    private boolean isNotRanked(Set<LottoNumber> matchedNumbers) {
        return matchedNumbers.size() < MINIMUM_RANK_SIZE;
    }

    private boolean isSecondRank(Set<LottoNumber> matchedNumbers, TotalWinningNumber totalWinningNumber) {
        return matchedNumbers.size() == SECOND_RANK_MATCHED && matchedNumbers.contains(totalWinningNumber.getBonusNumber());
    }

    private void putResult(int size, boolean isBonusBallMatched) {
        Rank rank = Rank.of(size, isBonusBallMatched);
        rankCounter.put(rank, rankCounter.get(rank) + 1);
    }

    public void calcProfitRatio(int amount) {
        profitRatio = Math.round((double) calcProfit() / amount * 100) / 100.0;
    }

    private int calcProfit() {
        int profit = 0;
        for (Rank rank : rankCounter.keySet()) {
            profit += rankCounter.get(rank) * rank.getPrize();
        }
        return profit;
    }

    public Map<Rank, Integer> getRankCounter() {
        return rankCounter;
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}
