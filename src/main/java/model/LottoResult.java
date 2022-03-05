package model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private static final int INITIAL_RANK_COUNT = 0;

    private final Map<LottoRank, Integer> resultMap;

    public LottoResult(List<LottoRank> ranks) {
        this.resultMap = allocateResultFrom(ranks);
    }

    public BigDecimal getProfitRate(Budget budget) {
        return budget.getProfitRateFrom(getTotalPrize());
    }

    private BigDecimal getTotalPrize() {
        return resultMap.keySet().stream()
                .map(this::getTotalPrizeByRank)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalPrizeByRank(LottoRank lottoRank) {
        return lottoRank.multiplePrizeBy(getCountByRank(lottoRank));
    }

    private int getCountByRank(LottoRank rank) {
        return resultMap.get(rank);
    }

    private Map<LottoRank, Integer> allocateResultFrom(List<LottoRank> lottoRanks) {
        Map<LottoRank, Integer> result = initRankCount();
        for (LottoRank rank : lottoRanks) {
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    private Map<LottoRank, Integer> initRankCount() {
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
