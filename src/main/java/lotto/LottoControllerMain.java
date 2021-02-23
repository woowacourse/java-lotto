package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        int manualCount = InputView.inputManualPurchaseCount();
        int autoCount = money.totalCountOfPurchaseLotto() - manualCount;

        Lottos.createManualLotto(InputView.inputManualPurchase(manualCount));
        Lottos.createAutoLotto(autoCount);

        OutputView.showBuyLotto(manualCount, autoCount);

        WinningLotto winningLotto = new WinningLotto(new Lotto(InputView.winningNumbers()), new BonusNumber(InputView.bonusNumber()));
        List<Result> results = winningLotto.getWinningResult();

        OutputView.resultMessage(new Statistics(results), money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
