package lottogame;

import lottogame.domain.lotto.*;
import lottogame.domain.stats.Money;
import lottogame.domain.stats.Quantity;
import lottogame.domain.stats.LottoResults;
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
        Quantity manualQuantity = Quantity.of(InputView.scanManualQuantity());
        LottoGame lottoGame = new LottoGame(buyLottos(money, manualQuantity));
        WinningLotto winningLotto = new WinningLotto(
                confirmWinningLotto(InputView.scanWinningLotto()),
                LottoNumber.of(InputView.scanBonusNumber()));
        LottoResults lottoResults = lottoGame.results(winningLotto, money);
        OutputView.printSummary(lottoResults.values(), lottoResults.yield());
    }

    private static Lotto confirmWinningLotto(String winningLotto) {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(winningLotto);
        return manualLottoGenerator.generateLotto();
    }

    private static Lottos buyLottos(Money money, Quantity manualQuantity) {
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
