package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {
    private final List<PrizeType> lottoResults;

    public LottoResult(List<PrizeType> lottoResults) {
        this.lottoResults = new ArrayList<>(lottoResults);
    }

//    public double calculateProfitRate(Money money) {
//        int moneySum = 0;
//        for (PrizeType prizeType : result.keySet()) {
//            moneySum += prizeType.getPrizeMoney().getValue() * result.get(prizeType);
//        }
//        return (double) moneySum / money.getValue();
//    }

    public int getCountByPrizeType(PrizeType prizeType) {
        return (int) lottoResults.stream()
                .filter(p -> p.equals(prizeType))
                .count();
    }
}
