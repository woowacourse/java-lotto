package domain;

import java.util.Collections;
import java.util.Map;

public class WinningResult {

    private final Money purchaseLottoMoney;
    private final Map<Rank, Integer> winningResult;

    public WinningResult(Money purchaseLottoMoney, Map<Rank, Integer> winningResult) {
        this.purchaseLottoMoney = purchaseLottoMoney;
        this.winningResult = winningResult;
    }

    public double calculateRateOfReturn() {
        Money totalWinningMoney = new Money(0);
        for (Map.Entry<Rank, Integer> entry : winningResult.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();

            Money winningMoney = rank.calculateWinningMoney(count);
            totalWinningMoney = totalWinningMoney.sum(winningMoney);
        }
        return totalWinningMoney.divide(purchaseLottoMoney);
    }

    public Map<Rank, Integer> getWinningResult() {
        return Collections.unmodifiableMap(winningResult);
    }
}
