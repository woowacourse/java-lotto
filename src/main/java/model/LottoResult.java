package model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private static final int INITIAL_RANK_COUNT = 0;

    private final Money inputMoney;
    private final Map<LottoRank, Integer> resultMap;

    public LottoResult(Money inputMoney) {
        this.inputMoney = inputMoney;
        this.resultMap = new EnumMap<>(Map.of(
                LottoRank.FIRST, INITIAL_RANK_COUNT, LottoRank.SECOND, INITIAL_RANK_COUNT,
                LottoRank.THIRD, INITIAL_RANK_COUNT, LottoRank.FOURTH, INITIAL_RANK_COUNT,
                LottoRank.FIFTH, INITIAL_RANK_COUNT, LottoRank.NOTHING, INITIAL_RANK_COUNT)
        );
    }

    public BigDecimal getProfitRate() {
        return getTotalPrize().divide(inputMoney);
    }

    private Money getTotalPrize() {
        return resultMap.keySet().stream()
                .map(this::getTotalPrizeByRank)
                .reduce(Money.ZERO, Money::add);
    }

    private Money getTotalPrizeByRank(LottoRank lottoRank) {
        return lottoRank.getPrize().multiply(getCountByRank(lottoRank));
    }

    public int getCountByRank(LottoRank rank) {
        return resultMap.get(rank);
    }

    public void add(LottoRank rank) {
        resultMap.put(rank, getCountByRank(rank) + 1);
    }
}
