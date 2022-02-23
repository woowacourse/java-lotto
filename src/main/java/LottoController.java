import java.util.List;

public class LottoController {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();

        lottoGame.buyLotto(new Money(InputView.askMoneyInput()));

        OutputView.printLottosInformations(lottoGame.getLottos());

        List<Integer> winningNumbers = InputView.askWinningNumbers();
        int bonusNumber = InputView.askBonusNumber();
        lottoGame.getYield(winningNumbers, bonusNumber);
        OutputView.printWinningStatistic(lottoGame.getResults());
        OutputView.printYield(lottoGame.getYield(winningNumbers, bonusNumber));
    }
}
