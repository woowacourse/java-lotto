package domain;

import java.util.Map;

public class WinningResult {

    private final Money purchaseLottoMoney;
    private final Map<Winning, Integer> winningResult;

    public WinningResult(Money purchaseLottoMoney, Map<Winning, Integer> winningResult) {
        this.purchaseLottoMoney = purchaseLottoMoney;
        this.winningResult = winningResult;
    }

    public double calculateRateOfReturn() {
        Money totalWinningMoney = new Money(0);
        for (Map.Entry<Winning, Integer> entry : winningResult.entrySet()) {
            Winning winning = entry.getKey();
            int count = entry.getValue();

            Money winningMoney = winning.calculateWinningMoney(count);
            totalWinningMoney = totalWinningMoney.sum(winningMoney);
        }
        return purchaseLottoMoney.divide(totalWinningMoney);
    }

    public Map<Winning, Integer> getWinningResult() {
        return winningResult;
    }
}
