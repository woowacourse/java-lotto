package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Money inputMoney;
    private final Map<LottoRank, Integer> resultMap;

    public LottoResult(Money inputMoney) {
        this.inputMoney = inputMoney;
        this.resultMap = new HashMap<>(Map.of(LottoRank.FIRST, 0, LottoRank.SECOND, 0,
                LottoRank.THIRD, 0, LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 0, LottoRank.NOTHING, 0));
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

    public BigDecimal getProfitRate() {
        return getTotalPrize().divide(inputMoney);
    }
}
