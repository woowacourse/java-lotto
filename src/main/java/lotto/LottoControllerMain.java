package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        PurchaseCount purchaseCount = new PurchaseCount( money.totalCountOfPurchaseLotto(), InputView.inputManualPurchaseCount());

        Lottos lottos = new Lottos();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        for (int i = 0; i < purchaseCount.getManualPurchaseCount(); i++) {
            lottos.buyLotto(new LottoManualGenerator(), InputView.inputManualPurchase());
        }

        for (int i = 0; i < purchaseCount.getAutoPurchaseCount(); i++) {
            lottos.buyLotto(new LottoAutoGenerator());
        }

        OutputView.showBuyLotto(lottos, purchaseCount);

        WinningLotto winningLotto = new WinningLotto(new Lotto(InputView.winningNumbers()), new LottoNumber(InputView.bonusNumber()));
        List<Result> results = winningLotto.getWinningResult(lottos);

        OutputView.resultMessage();
        OutputView.result(new Statistics(results));
        OutputView.showTotalProfit(money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
