import domain.LottoGame;
import domain.Money;
<<<<<<< HEAD
import domain.RandomLottoNumberGenerator;
=======
>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Money money = new Money(InputView.askMoneyInput());
<<<<<<< HEAD
        LottoGame lottoGame = LottoGame.startLottoGame(new RandomLottoNumberGenerator(), money);
=======
        LottoGame lottoGame = LottoGame.startLottoGame(money);
>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b

        OutputView.printLottosInformations(lottoGame.getLottos());

        lottoGame.enterWinningLottoNumbersAndBonusNumber(InputView.askWinningNumbers(), InputView.askBonusNumber());

        OutputView.printWinningStatistic(lottoGame.produceResults());
        OutputView.printYield(lottoGame.calculateYield());
    }
}
