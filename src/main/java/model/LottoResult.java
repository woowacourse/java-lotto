package model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private static final int INITIAL_RANK_COUNT = 0;

    private final Budget inputBudget;
    private final Map<LottoRank, Integer> resultMap;

    public LottoResult(Budget inputBudget, List<LottoRank> ranks) {
        this.inputBudget = inputBudget;
        resultMap = setInitialRankCount();
        allocateResultFrom(ranks);
    }

    public BigDecimal getProfitRate() {
        return inputBudget.getProfitRateFrom(getTotalPrize());
    }

    private BigDecimal getTotalPrize() {
        return resultMap.keySet().stream()
                .map(this::getTotalPrizeByRank)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalPrizeByRank(LottoRank lottoRank) {
        return lottoRank.multiplePrizeBy(getCountByRank(lottoRank));
    }

    public int getCountByRank(LottoRank rank) {
        return resultMap.get(rank);
    }

    private void allocateResultFrom(List<LottoRank> lottoRanks) {
        for (LottoRank rank : lottoRanks) {
            resultMap.put(rank, resultMap.get(rank) + 1);
        }
    }

    private Map<LottoRank, Integer> setInitialRankCount() {
        return new EnumMap<>(Map.of(
                LottoRank.FIRST, INITIAL_RANK_COUNT, LottoRank.SECOND, INITIAL_RANK_COUNT,
                LottoRank.THIRD, INITIAL_RANK_COUNT, LottoRank.FOURTH, INITIAL_RANK_COUNT,
                LottoRank.FIFTH, INITIAL_RANK_COUNT, LottoRank.NOTHING, INITIAL_RANK_COUNT)
        );
    }

    public Map<LottoRank, Integer> getResultMap() {
        return resultMap;
    }
}
