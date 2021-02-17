package lotto;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Result;
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

        List<Integer> winningNumbers = InputView.winningNumbers(scanner);
        int bonusNumber = InputView.bonusNumber(scanner, winningNumbers);

        // TODO:
        //  Result에서 총 수익 계산하는 로직
        List<Result> results = lottos.getResults(winningNumbers, bonusNumber);
        OutputView.result(results, money.calculateProfit(0));
    }
}
