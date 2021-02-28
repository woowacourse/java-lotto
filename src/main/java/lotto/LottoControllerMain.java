package lotto;

import lotto.domain.*;
import lotto.util.LottoFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        PurchaseCount purchaseCount = new PurchaseCount( money.totalCountOfPurchaseLotto(), InputView.inputManualPurchaseCount());

        LottoFactory manualLotto = LottoFactory.of(InputView.inputManualPurchase(purchaseCount.getManualPurchaseCount()));
        LottoFactory autoLotto = LottoFactory.of(purchaseCount.getAutoPurchaseCount());

        OutputView.showBuyLotto(manualLotto, autoLotto);

        WinningLotto winningLotto = new WinningLotto(new Lotto(InputView.winningNumbers()), new LottoNumber(InputView.bonusNumber()));
        List<Result> results = winningLotto.getWinningResult(manualLotto, autoLotto);

        OutputView.resultMessage();
        OutputView.result(new Statistics(results));
        OutputView.showTotalProfit(money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
