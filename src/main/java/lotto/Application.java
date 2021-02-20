package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Scanner;

import static lotto.domain.LottoNumber.createLottoNumbers;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Money money = new Money(InputView.takeMoneyInput(scanner));
        LottoGenerator lottoGenerator = new LottoGenerator();

        Lottos lottos = new Lottos(lottoGenerator.createLottos(money.calculateAffordableNumberOfLotto()));
        OutputView.showLottos(lottos);

        WinningLotto winningLotto = new WinningLotto(
                new Lotto(createLottoNumbers(InputView.takeWinningNumbersInput(scanner))),
                new LottoNumber(InputView.takeBonusNumberInput(scanner))
        );

        List<Result> results = lottos.getResults(winningLotto);
        OutputView.result(results, money.calculateProfitRate(Result.calculateProfit(results)));
    }
}
