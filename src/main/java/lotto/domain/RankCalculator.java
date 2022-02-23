package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RankCalculator {

    private static final int MINIMUM_RANK_SIZE = 3;
    private static final int INIT_NUMBER = 0;
    private static final int SECOND_RANK_SIZE = 5;
    private static final int SECOND_RANK_MATCHED = 6;

    private final Map<Rank, Integer> result = new HashMap<>();

    public RankCalculator() {
        initResult();
    }

    private void initResult() {
        result.put(Rank.FIRST, INIT_NUMBER);
        result.put(Rank.SECOND, INIT_NUMBER);
        result.put(Rank.THIRD, INIT_NUMBER);
        result.put(Rank.FOURTH, INIT_NUMBER);
        result.put(Rank.FIFTH, INIT_NUMBER);
    }

    public Map<Rank, Integer> calcRank(TotalNumber totalNumber, List<Set<LottoNumber>> tickets) {
        Set<LottoNumber> winningAndBonusNumber = totalNumber.getWinningAndBonusNumber();
        for (Set<LottoNumber> ticket : tickets) {
            ticket.retainAll(winningAndBonusNumber);
            calcRankForEach(ticket, totalNumber);
        }
        return result;
    }

    private void calcRankForEach(Set<LottoNumber> ticket, TotalNumber totalNumber) {
        if (isNotRanked(ticket)) {
            return;
        }
        if (isSecondRank(ticket, totalNumber)) {
            putResult(SECOND_RANK_SIZE, true);
            return;
        }
        putResult(ticket.size(), false);
    }

    private boolean isNotRanked(Set<LottoNumber> ticket) {
        return ticket.size() < MINIMUM_RANK_SIZE;
    }

    private boolean isSecondRank(Set<LottoNumber> ticket, TotalNumber totalNumber) {
        return ticket.size() == SECOND_RANK_MATCHED && ticket.contains(totalNumber.getBonusNumber());
    }

    private void putResult(int size, boolean isBonusBallMatched) {
        Rank rank = Rank.of(size, isBonusBallMatched);
        result.put(rank, result.get(rank) + 1);
    }

    public double calcProfitRatio(int amount) {
        return Math.round((double) calcProfit() / amount * 100) / 100.0;
    }

    private int calcProfit() {
        int profit = 0;
        for (Rank rank : result.keySet()) {
            profit += result.get(rank) * rank.getPrize();
        }
        return profit;
    }
}
