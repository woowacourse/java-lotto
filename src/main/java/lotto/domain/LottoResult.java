package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {
    private final List<PrizeType> lottoResults;

    public LottoResult(List<PrizeType> lottoResults) {
        this.lottoResults = new ArrayList<>(lottoResults);
    }

    public double calculateProfitRate(Money money) {
        double moneySum = 0;
        for (PrizeType prizeType : PrizeType.values()) {
            moneySum += prizeType.getPrizeMoney().getValue() * getCountByPrizeType(prizeType);
        }
        return moneySum / money.getValue();
    }

    public int getCountByPrizeType(PrizeType prizeType) {
        return (int) lottoResults.stream()
                .filter(p -> p.equals(prizeType))
                .count();
    }
}
