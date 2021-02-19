package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoResult {
    private final List<Prize> lottoResults;

    public LottoResult(List<Prize> lottoResults) {
        this.lottoResults = new ArrayList<>(lottoResults);
    }

    public double calculateProfitRate() {
        return Prize.calculatePrizeMoneySum(lottoResults);
    }

    public int getCountPerPrizeType(Prize prize) {
        return Prize.getCountByPrizeType(lottoResults,prize);
    }
}
