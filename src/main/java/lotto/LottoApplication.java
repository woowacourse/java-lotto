package lotto;

import lotto.domain.game.TotalLottoGames;
import lotto.domain.game.ManualCount;
import lotto.domain.game.Count;
import lotto.domain.lotto.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.getPurchaseAmount();
        Count totalCount = InputView.countFrom(purchaseAmount);
        ManualCount manualCount = InputView.getManualCount(totalCount);
        TotalLottoGames totalLottoGames = InputView.getTotalLottoGames(totalCount, manualCount);
        OutputView.lottoList(totalLottoGames);
        Lotto winningNumber = InputView.getWinLotto();
        WinningLotto winningLotto = InputView.getWinningLotto(winningNumber);
        OutputView.winList(totalLottoGames, winningLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }
}
