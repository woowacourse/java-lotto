package lottogame;

import lottogame.domain.lotto.*;
import lottogame.domain.stats.Money;
import lottogame.domain.stats.Quantity;
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
        Money money = Money.of(InputView.scanMoney());
        Lottos myLottos = buyLottos(money);
        WinningLotto winningLotto = confirmWinningLotto();
        LottoGame lottoGame = new LottoGame(myLottos, winningLotto);
        summarize(lottoGame, money);
    }

    private static void summarize(LottoGame lottoGame, Money money) {
        LottoResults lottoResults = lottoGame.results();
        Yield yield = Yield.of(lottoResults.totalPrizeMoney(), money);
        OutputView.printSummary(lottoResults.values(), yield.value());
    }

    private static WinningLotto confirmWinningLotto() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(InputView.scanWinningLotto());
        LottoNumber bonusNumber = LottoNumber.of(InputView.scanBonusNumber());
        return new WinningLotto(manualLottoGenerator.generateLotto(), bonusNumber);
    }

    private static Lottos buyLottos(Money money) {
        Quantity manualQuantity = Quantity.of(InputView.scanManualQuantity());
        Quantity autoQuantity = Quantity.from(money, manualQuantity);
        Lottos lottos = createLottos(manualQuantity, autoQuantity);
        OutputView.printLottoQuantity(manualQuantity.value(), autoQuantity.value());
        OutputView.printLottos(lottos.values());
        return lottos;
    }

    private static Lottos createLottos(Quantity manualQuantity, Quantity autoQuantity) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(generateLottos(manualQuantity, new ManualLottoGenerator()));
        lottos.addAll(generateLottos(autoQuantity, new AutoLottoGenerator(autoQuantity)));
        return new Lottos(lottos);
    }

    private static List<Lotto> generateLottos(Quantity quantity, LottoGenerator lottoGenerator) {
        if (lottoGenerator instanceof ManualLottoGenerator && !quantity.isZero()) {
            ((ManualLottoGenerator) lottoGenerator).addResources(InputView.scanLottos(quantity.value()));
        }
        if (quantity.isZero()) {
            return new ArrayList<>();
        }
        return lottoGenerator.generateLottos();
    }
}
