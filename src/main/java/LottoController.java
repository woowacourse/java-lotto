import static view.InputView.askManualLottoNumbers;

import domain.LottoGame;
import domain.LottoGenerators;
import domain.ManualLottoGenerator;
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

        int autoAmount = money.convertToAmount() - manualAmount;

        LottoGame lottoGame = new LottoGame(money,
            new LottoGenerators(new ManualLottoGenerator(manualAmount, manualLottoNumbers),
                new RandomLottoGenerator(autoAmount)));

        OutputView.printLottosInformations(lottoGame.getLottos());

        WinningNumbers winningNumbers = InputView.askWinningNumbers();

        WinningChecker winningChecker = lottoGame.makeResult(winningNumbers);

        OutputView.printWinningStatistic(winningChecker);
        OutputView.printYield(lottoGame.getYield());
    }
}
