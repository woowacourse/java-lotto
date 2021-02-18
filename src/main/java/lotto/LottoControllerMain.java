package lotto;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoControllerMain {
    public static void main(String[] args) {
        Money money = new Money(InputView.takeMoneyInput());
        Lottos lottos = new Lottos(money.calculateNumberOfLotto());
        OutputView.showLottos(lottos);

        List<Integer> winningNumbers = InputView.winningNumbers();
        int bonusNumber = InputView.bonusNumber(winningNumbers);

        List<Result> results = lottos.getResults(winningNumbers, bonusNumber);
        OutputView.result(results, money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
