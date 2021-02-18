package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {
    private final List<Prize> lottoResults;

    public LottoResult(List<Prize> lottoResults) {
        this.lottoResults = new ArrayList<>(lottoResults);
    }

    public double calculateProfitRate(Money money) {
        return Prize.calculatePrizeMoneySum(lottoResults, money);
    }

    public int getCountPerPrizeType(Prize prize) {
        return Prize.getCountByPrizeType(lottoResults,prize);
    }
}
