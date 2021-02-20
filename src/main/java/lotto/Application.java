package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LottoMachine lottoMachine = LottoMachine.getInstance();
        Money money = new Money(InputView.takeMoneyInput(scanner));

        Lottos lottos = new Lottos(lottoMachine.createLottos(new AutomaticLottoGenerator(), money.calculateAffordableNumberOfLotto()));
        OutputView.showLottos(lottos);

        WinningLotto winningLotto = new WinningLotto(
                new ManualLottoGenerator(InputView.takeWinningNumbersInput(scanner)).createLotto(),
                new LottoNumber(InputView.takeBonusNumberInput(scanner))
        );

        List<Rank> ranks = lottos.getResults(winningLotto);
        OutputView.result(new LottoStatistics(ranks, money));
    }
}
