package domain;

import java.util.ArrayList;
import java.util.List;
import util.RandomNumbersGenerator;

public class LottoManager {
    public LottoWallet generateLottos(final int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; ++i) {
            lottos.add(new Lotto(RandomNumbersGenerator.generateUniqueNumbers(LottoNumber.MIN, LottoNumber.MAX, 6)));
        }
        return new LottoWallet(lottos);
    }

    public WinningResult calculateWinningResult(LottoWallet lottoWallet, WinningLotto winningLotto) {
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : lottoWallet.getLottoWallet()) {
            final int matchedCount = winningLotto.countMatchedNumber(lotto);
            final boolean isBonusMatched = winningLotto.isBonusNumberMatched(lotto);
            WinningInfo winningInfo = WinningInfo.of(matchedCount, isBonusMatched);
            winningResult.increaseCount(winningInfo, 1);
        }
        return winningResult;
    }

    public float calculateRevenue(WinningResult winningResult, Money money) {
        return (float) winningResult.getTotalPrices() / money.getValue();
    }
}
