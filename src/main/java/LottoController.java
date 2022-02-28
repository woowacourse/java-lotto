import domain.Lotto;
import domain.LottoGame;
import domain.LottoGenerator;
import domain.Money;
import domain.RandomLottoGenerator;
import domain.WinningChecker;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {

        LottoGame lottoGame = new LottoGame(new Money(InputView.askMoneyInput()),
            new RandomLottoGenerator());

        OutputView.printLottosInformations(lottoGame.getLottos());

        WinningNumbers winningNumbers = InputView.askWinningNumbers();

        lottoGame.makeResult(winningNumbers);

        OutputView.printWinningStatistic();
        OutputView.printYield(lottoGame.getYield());
    }
}
