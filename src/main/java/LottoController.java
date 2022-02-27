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

        List<Integer> lottoNumbers = InputView.askLottoNumbers();

        lottoGame.produceResults();
        OutputView.printWinningStatistic();
        OutputView.printYield(lottoGame.getYield());
    }
}
