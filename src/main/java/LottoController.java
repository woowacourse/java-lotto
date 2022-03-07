import domain.LottoGenerator;

import domain.Lottos;

import domain.Money;

import domain.WinningChecker;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {

        Money money = new Money(InputView.askMoneyInput(), InputView.askManualAmount());

        LottoGenerator lottoGenerator = new LottoGenerator(
            InputView.askManualLottoNumbers(money.getManualAmount()), money.getAutoAmount());

        Lottos lottos = new Lottos(lottoGenerator.generate());

        OutputView.printLottosInformation(money, lottos);

        WinningChecker winningChecker = new WinningChecker(lottos, InputView.askWinningNumbers());

        winningChecker.check();

        OutputView.printWinningStatistic(winningChecker.getStatisticMap());
        OutputView.printYield(winningChecker.getYield());
    }
}
