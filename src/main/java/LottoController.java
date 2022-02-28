import domain.LottoGame;
import domain.Money;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();

        lottoGame.buyLotto(new Money(InputView.askMoneyInput()));

        OutputView.printLottosInformations(lottoGame.getLottos());

        WinningNumbers winningNumbers = InputView.askWinningNumbers();

        lottoGame.makeResult(winningNumbers.getWinningNumbers(), winningNumbers.getBonusNumber());
        OutputView.printWinningStatistic();
        OutputView.printYield(lottoGame.getYield());
    }
}
