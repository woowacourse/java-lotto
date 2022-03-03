import domain.LottoGame;
import domain.Money;
import domain.RandomLottoGenerator;
import domain.WinningChecker;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {

        Money money = new Money(InputView.askMoneyInput());

        int manulAmount = InputView.askManualAmount(money);

        LottoGame lottoGame = new LottoGame(money,
            new RandomLottoGenerator(money.convertToAmount()));

        OutputView.printLottosInformations(lottoGame.getLottos());

        WinningNumbers winningNumbers = InputView.askWinningNumbers();

        WinningChecker winningChecker = lottoGame.makeResult(winningNumbers);

        OutputView.printWinningStatistic(winningChecker);
        OutputView.printYield(lottoGame.getYield());
    }
}
