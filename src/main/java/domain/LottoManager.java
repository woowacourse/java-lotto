package domain;

import java.util.ArrayList;
import java.util.List;
import util.RandomNumbersGenerator;

public class LottoManager {
    public List<Lotto> generateLottos(final int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; ++i) {
            lottos.add(new Lotto(RandomNumbersGenerator.generateUniqueNumbers(Number.MIN, Number.MAX, 6)));
        }
        return lottos;
    }

    public WinningResult calculateWinningResult(List<Lotto> lottos, WinningLotto winningLotto) {
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : lottos) {
            final int matchedCount = winningLotto.getMatchedCount(lotto);
            final boolean isBonusMatched = winningLotto.isMatchBonus(lotto);
            WinningInfo winningInfo = WinningInfo.of(matchedCount, isBonusMatched);
            winningResult.increaseCount(winningInfo, 1);
        }
        return winningResult;
    }

    public float calculateRevenue(WinningResult winningResult, Money money) {
        return (float) winningResult.getTotalPrices() / money.getValue();
    }
}
