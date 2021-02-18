package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        Lottos lottos = new Lottos(money.buyLotto());

        OutputView.showBuyLotto(lottos.getLottos());

        List<Integer> winningNumbers = InputView.winningNumbers();
        int bonusNumber = InputView.bonusNumber(winningNumbers);

        List<Result> results = lottos.getResults(winningNumbers, bonusNumber);
        OutputView.result(results, money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
