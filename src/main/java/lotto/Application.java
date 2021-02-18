package lotto;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Result;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = InputView.takeMoneyInput(scanner);
        Money money = new Money(input);
        Lottos lottos = new Lottos(money.calculateNumberOfLotto());
        OutputView.showLottos(lottos);

        WinningLotto winningLotto = new WinningLotto(
                InputView.takeWinningNumbersInput(scanner),
                InputView.takeBonusNumberInput(scanner)
        );

        List<Result> results = lottos.getResults(winningLotto);
        OutputView.result(results, money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
