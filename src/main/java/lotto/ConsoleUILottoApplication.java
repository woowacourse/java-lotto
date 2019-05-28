package lotto;

import lotto.domain.*;
import lotto.domain.generator.LottoNosManualGenerator;
import lotto.domain.generator.LottosGenerator;
import lotto.view.InputConsoleView;
import lotto.view.InputView;
import lotto.view.OutputConsoleView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleUILottoApplication {
    private static InputView inputView = new InputConsoleView();
    private static OutputView outputView = new OutputConsoleView();

    public static void main(String[] args) {
        Money money = Money.from(inputView.inputMoney());
        int countOfManual = inputView.inputCountOfManual();
        List<Lotto> userLottos = LottosGenerator.of(countOfManual, money.getCountOfPurchase(), inputView).generate();
        outputView.printLottos(userLottos, countOfManual, money.getCountOfPurchase());

        WinningLotto winningLotto = generateWinningLotto();
        WinPrize winPrize = getWinPrize(userLottos, winningLotto);

        outputView.printResult(winPrize);
        outputView.printRateOfProfit(money, winPrize);
    }

    private static WinningLotto generateWinningLotto() {
        Lotto winLotto = Lotto.of(new LottoNosManualGenerator(inputView.inputWinningLotto()).generate());
        LottoNo bonusNo = LottoNo.from(inputView.inputBonusNo());
        return new WinningLotto(winLotto, bonusNo);
    }

    private static WinPrize getWinPrize(final List<Lotto> userLottos, final WinningLotto winningLotto) {
        WinPrize winPrize = new WinPrize();
        for (final Lotto userLotto : userLottos) {
            winPrize.addWinCount(winningLotto.match(userLotto));
        }
        return winPrize;
    }
}
