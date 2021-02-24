package lottogame;

import lottogame.domain.Money;
import lottogame.domain.Quantity;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoGame;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.lotto.WinningLotto;
import lottogame.domain.stats.LottoResults;
import lottogame.utils.AutoLottoGenerator;
import lottogame.utils.ComplexLottoGenerator;
import lottogame.utils.ManualLottoGenerator;
import lottogame.view.InputView;
import lottogame.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Money money = money();
        Quantity manualQuantity = manualQuantity();
        LottoGame lottoGame = new LottoGame(lottos(money, manualQuantity));
        printPurchasedLottos(lottoGame.lottos(), manualQuantity.value());
        WinningLotto winningLotto = inputWinningLotto();
        LottoResults lottoResults = lottoGame.Results(winningLotto);
        OutputView.printResult(lottoResults.values(), lottoResults.calculateYield(money));
    }

    private static List<Lotto> lottos(Money money, Quantity manualQuantity) {
        Quantity autoQuantity = Quantity.ofInt(money.getMoney() / 1000).subtract(manualQuantity);
        ComplexLottoGenerator complexLottoGenerator = new ComplexLottoGenerator(
                new ManualLottoGenerator(InputView.askLottoNumbers(manualQuantity.value())),
                new AutoLottoGenerator(autoQuantity)
        );
        return complexLottoGenerator.generateLottos();
    }

    private static Money money() {
        return new Money(InputView.inputMoney());
    }

    private static Quantity manualQuantity() {
        return Quantity.ofString(InputView.askManualQuantity());
    }

    private static void printPurchasedLottos(Lottos lottos, int manualCount) {
        OutputView.showLottoQuantity(lottos.values().size(), manualCount);
        OutputView.showLottos(lottos.values());
    }

    private static WinningLotto inputWinningLotto() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(InputView.inputWinningLottoNumbers());
        String bonusNumber = InputView.inputBonusNumber();
        return new WinningLotto(manualLottoGenerator.generateLotto(), bonusNumber);
    }
}
