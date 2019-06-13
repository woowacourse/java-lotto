package lotto.domain;

import java.util.Map;
import java.util.Set;

public class LottoResult_VO {

    private final Map<Rank, Integer> result;
    private final int price;
    private static final int PERSENT = 100;

    public LottoResult_VO(Map<Rank, Integer> result, int price) {
        this.result = result;
        this.price = price;
    }

    public Set<Rank> getResultKey() {
        return result.keySet();
    }

    public int getResultValue(Rank result) {
        return this.result.get(result);
    }

    public double dividendRate() {
        double rateResult = 0;
        for (Rank rank : result.keySet()) {
            rateResult += rank.getWinningMoney() * result.get(rank);
        }

        return rateResult / price * PERSENT;
    }

}
