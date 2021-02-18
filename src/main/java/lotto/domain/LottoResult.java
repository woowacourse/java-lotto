package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {
    private final List<Prize> lottoResults;

    public LottoResult(List<Prize> lottoResults) {
        this.lottoResults = new ArrayList<>(lottoResults);
    }

    public double calculateProfitRate(Money money) {
        double moneySum = 0;
        for (Prize prize : Prize.values()) {
            moneySum += prize.getPrizeMoney().getValue() * getCountByPrizeType(prize);
        }
        return moneySum / money.getValue();
    }

    public int getCountByPrizeType(Prize prize) {
        return (int) lottoResults.stream()
                .filter(p -> p.equals(prize))
                .count();
    }
}
