import domain.LottoGame;
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

        LottoGame lottoGame = new LottoGame(lottoGenerator);

        OutputView.printLottosInformation(money, lottos);

        WinningChecker winningChecker = lottoGame.makeResult(InputView.askWinningNumbers());

        OutputView.printWinningStatistic(winningChecker);
        OutputView.printYield(lottoGame.getYield(winningChecker));
    }
}
