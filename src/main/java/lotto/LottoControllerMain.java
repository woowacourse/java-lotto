package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        Lottos lottos = new Lottos(money.countBuyLotto());

        OutputView.showBuyLotto(lottos.getLottos());

        List<Result> results = lottos.checkLottoResults(
                new WinningNumber(InputView.winningNumbers()),
                new BonusNumber(InputView.bonusNumber())
        );

        OutputView.resultMessage();
        OutputView.result(Result.getResultValues(), new Statistics(results));
        OutputView.showTotalProfit(money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
