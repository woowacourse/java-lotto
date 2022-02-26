package lotto;

import lotto.domain.LottoPurchaseCounts;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.RandomLottoMachine;
import lotto.domain.WinLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final Money money = new Money(InputView.inputMoney());
        final LottoPurchaseCounts purchaseCounts = money.calculatePurchaseCounts(InputView.inputPurchaseManualCount());
        OutputView.outputBuyLottoCounts(purchaseCounts);

        final Lottos manualLottos = Lottos.from(InputView.inputManualLottos(purchaseCounts.getManualCount()));
        final Lottos lottos = RandomLottoMachine.buyLotto(manualLottos.getLottos(), purchaseCounts.getAutomaticCount());
        OutputView.outputLottos(lottos.getLottos());

        final WinLotto winLotto = WinLotto.of(InputView.inputWinLotto(), InputView.inputBonusNumber());
        OutputView.outputResult(lottos.createResult(winLotto));
    }
}
