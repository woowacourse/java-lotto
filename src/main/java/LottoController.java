import domain.LottoGame;
import domain.LottoGenerators;
import domain.ManualLottoGenerator;
import domain.Money;
import domain.AutoLottoGenerator;
import domain.WinningChecker;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {

        Money money = new Money(InputView.askMoneyInput(), InputView.askManualAmount());

        LottoGame lottoGame = new LottoGame(
            new LottoGenerators(
                new ManualLottoGenerator(InputView.askManualLottoNumbers(money.getManualAmount())),
                new AutoLottoGenerator(money.getAutoAmount())));

        OutputView.printLottosInformation(money, lottoGame.getLottos());

        WinningChecker winningChecker = lottoGame.makeResult(InputView.askWinningNumbers());

        OutputView.printWinningStatistic(winningChecker);
        OutputView.printYield(lottoGame.getYield(winningChecker));
    }
}
