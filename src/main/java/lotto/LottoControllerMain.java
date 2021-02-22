package lotto;

import lotto.domain.*;
import lotto.util.LottoFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        int manualCount = InputView.inputManualPurchaseCount();
        int autoCount = money.totalCountOfPurchaseLotto() - manualCount;

        Lottos lottos = new Lottos(
                LottoFactory.createManualLotto(InputView.inputManualPurchase(manualCount)),
                LottoFactory.createAutoLotto(autoCount)
        );

        OutputView.showBuyLotto(lottos);

        WinningLotto winningLotto = new WinningLotto(new Lotto(InputView.winningNumbers()), new BonusNumber(InputView.bonusNumber()));
        List<Result> results = winningLotto.getWinningResult(lottos);

        OutputView.resultMessage();
        OutputView.result(new Statistics(results));
        OutputView.showTotalProfit(money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
