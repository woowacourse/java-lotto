import static view.InputView.askManualLottoNumbers;

import domain.LottoGame;
import domain.Money;
import domain.RandomLottoGenerator;
import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {

        Money money = new Money(InputView.askMoneyInput());

        int manualAmount = InputView.askManualAmount(money);

        List<List<Integer>> manualLottoNumbers = InputView.askManualLottoNumbers(manualAmount);
        System.out.println(manualLottoNumbers);

        LottoGame lottoGame = new LottoGame(money,
            new RandomLottoGenerator(money.convertToAmount()));

        OutputView.printLottosInformations(lottoGame.getLottos());

        WinningNumbers winningNumbers = InputView.askWinningNumbers();

        WinningChecker winningChecker = lottoGame.makeResult(winningNumbers);

        OutputView.printWinningStatistic(winningChecker);
        OutputView.printYield(lottoGame.getYield());
    }
}
