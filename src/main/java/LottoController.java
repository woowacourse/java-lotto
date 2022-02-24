import domain.LottoGame;
import domain.Money;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();

        lottoGame.buyLotto(new Money(InputView.askMoneyInput()));

        OutputView.printLottosInformations(lottoGame.getLottos());

        List<Integer> winningNumbers = InputView.askWinningNumbers();
        int bonusNumber = InputView.askBonusNumber();
        lottoGame.makeResult(winningNumbers, bonusNumber);
        //view.OutputView.printWinningStatistic(lottoGame.getResults());
        OutputView.printWinningStatistic();
        OutputView.printYield(lottoGame.getYield());
    }
}
