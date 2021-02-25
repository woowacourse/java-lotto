package lottogame;

import lottogame.domain.stats.Money;
import lottogame.domain.stats.Quantity;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoGame;
import lottogame.domain.lotto.WinningLotto;
import lottogame.domain.stats.LottoResults;
import lottogame.domain.stats.Yield;
import lottogame.utils.AutoLottoGenerator;
import lottogame.utils.LottoGenerator;
import lottogame.utils.ManualLottoGenerator;
import lottogame.view.InputView;
import lottogame.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Money money = Money.of(InputView.inputMoney());
        List<Lotto> myLottos = buyLottos(money);
        WinningLotto winningLotto = confirmWinningLotto();
        LottoGame lottoGame = new LottoGame(myLottos, winningLotto);
        summarizeResults(lottoGame, money);
    }

    private static void summarizeResults(LottoGame lottoGame, Money money) {
        LottoResults lottoResults = lottoGame.results();
        Yield yield = new Yield(lottoResults.calculateWinningAmount(), money);
        OutputView.printResult(lottoResults.values(), yield.value());
    }

    private static WinningLotto confirmWinningLotto() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(InputView.inputWinningLottoNumbers());
        String bonusNumber = InputView.inputBonusNumber();
        return new WinningLotto(manualLottoGenerator.generateLotto(), bonusNumber);
    }

    private static List<Lotto> buyLottos(Money money) {
        Quantity manualQuantity = Quantity.of(InputView.askManualQuantity());
        Quantity autoQuantity = Quantity.from(money, manualQuantity);
        List<Lotto> lottos = createLottos(manualQuantity, autoQuantity);
        printPurchasedLottos(lottos, manualQuantity.value());
        return lottos;
    }

    private static void printPurchasedLottos(List<Lotto> lottos, int manualCount) {
        OutputView.showLottoQuantity(lottos.size(), manualCount);
        OutputView.showLottos(lottos);
    }

    private static List<Lotto> createLottos(Quantity manualQuantity, Quantity autoQuantity) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(generateLottos(manualQuantity, new ManualLottoGenerator()));
        lottos.addAll(generateLottos(autoQuantity, new AutoLottoGenerator(autoQuantity)));
        return lottos;
    }

    private static List<Lotto> generateLottos(Quantity quantity, LottoGenerator lottoGenerator) {
        if (lottoGenerator instanceof ManualLottoGenerator && !quantity.isZero()) {
            ((ManualLottoGenerator) lottoGenerator).addResources(InputView.askLottoNumbers(quantity.value()));
        }
        if (quantity.isZero()) {
            return new ArrayList<>();
        }
        return lottoGenerator.generateLottos();
    }
}
