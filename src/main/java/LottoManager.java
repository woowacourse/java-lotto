import java.util.List;

public class LottoManager {

    public WinningResult getWinningResult(List<Lotto> lottos, WinningLotto winningLotto) {
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : lottos) {
            final int matchedCount = winningLotto.getMatchedCount(lotto);
            final boolean isBonusMatched = winningLotto.isMatchBonus(lotto);
            WinningInfo winningInfo = WinningInfo.of(matchedCount, isBonusMatched);
            winningResult.increaseCount(winningInfo, 1);
        }
        return winningResult;
    }

    public float getRevenue(WinningResult winningResult, Money money) {
        return (float) winningResult.getTotalPrices() / money.getValue();
    }
}
