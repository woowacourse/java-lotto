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

        Lottos lottos = new Lottos(new ManualLotto(InputView.inputManualPurchase(manualCount)), autoCount);

        OutputView.showBuyLotto(manualCount, lottos.getLottos());

        WinningLotto winningLotto = new WinningLotto(new Lotto(InputView.winningNumbers()), new BonusNumber(InputView.bonusNumber()));
        List<Result> results = winningLotto.getWinningResult(lottos.getLottos());

        OutputView.resultMessage();
        OutputView.result(new Statistics(results));
        OutputView.showTotalProfit(money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
