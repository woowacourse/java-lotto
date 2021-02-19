package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoResult {
    private final List<Prize> lottoResults;

    public LottoResult(List<Prize> lottoResults) {
        this.lottoResults = new ArrayList<>(lottoResults);
    }

    public List<Prize> lottoResult() {
        return Collections.unmodifiableList(lottoResults);
    }

    public int getCountPerPrizeType(Prize prize) {
        return Prize.getCountByPrizeType(lottoResults, prize);
    }

    public Money getTotalProfit() {
        Money totalProfit = Money.ZERO;
        for (Prize prize : lottoResults) {
            totalProfit = totalProfit.plus(prize.getPrizeMoney());
        }
        return totalProfit;
    }
}
