import domain.LottoGame;
import domain.Money;
import domain.RandomLottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Money money = new Money(InputView.askMoneyInput());
        LottoGame lottoGame = LottoGame.startLottoGame(new RandomLottoNumberGenerator(), money);

        OutputView.printLottosInformations(lottoGame.getLottos());

        lottoGame.enterWinningLottoNumbersAndBonusNumber(InputView.askWinningNumbers(), InputView.askBonusNumber());

        OutputView.printWinningStatistic(lottoGame.produceResults());
        OutputView.printYield(lottoGame.calculateYield());
    }
}
