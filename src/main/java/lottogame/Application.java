package lottogame;

import lottogame.domain.Money;
import lottogame.domain.Quantity;
import lottogame.domain.lotto.LottoGame;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.lotto.WinningLotto;
import lottogame.domain.stats.LottoResults;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Money money = money();
        Quantity manualQuantity = manualQuantity();
        LottoGame lottoGame = new LottoGame(money, manualQuantity);
        if (lottoGame.hasShortage()) {
            lottoGame.addLottos(InputView.askLottoNumbers(lottoGame.shortage()));
        }
        printPurchasedLottos(lottoGame.lottos(), manualQuantity.value());
        WinningLotto winningLotto = inputWinningLotto();
        LottoResults lottoResults = lottoGame.Results(winningLotto);
        OutputView.printResult(lottoResults.values(), lottoResults.calculateYield(money));
    }

    private static Money money() {
        return new Money(InputView.inputMoney());
    }

    private static Quantity manualQuantity() {
        return Quantity.ofString(InputView.askManualQuantity());
    }

    private static Quantity totalQuantity() {
        Money money = new Money(InputView.inputMoney());
        return money.calculateQuantity();
    }

    private static void printPurchasedLottos(Lottos lottos, int manualCount) {
        OutputView.showLottoQuantity(lottos.values().size(), manualCount);
        OutputView.showLottos(lottos.values());
    }

    private static WinningLotto inputWinningLotto() {
        String numbers = InputView.inputWinningLottoNumbers();
        String bonusNumber = InputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusNumber);
    }
}
