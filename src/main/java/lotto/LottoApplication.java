package lotto;

import lotto.domain.LottoMachine;
import lotto.domain.LottoPurchaseCounts;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final Money money = createMoney();
        final LottoPurchaseCounts purchaseCounts = createPurchaseCounts(money);
        OutputView.outputBuyLottoCounts(purchaseCounts);

        final Lottos lottos = buyLotto(purchaseCounts);
        OutputView.outputLottos(lottos.getLottos());

        final WinLotto winLotto = createWinLotto();
        OutputView.outputResult(lottos.createResult(winLotto));
    }

    private static Money createMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            ErrorView.printErrorMessage(e);
            return createMoney();
        }
    }

    private static LottoPurchaseCounts createPurchaseCounts(final Money money) {
        try {
            return money.calculatePurchaseCounts(InputView.inputPurchaseManualCount());
        } catch (IllegalArgumentException e) {
            ErrorView.printErrorMessage(e);
            return createPurchaseCounts(money);
        }
    }

    private static Lottos buyLotto(final LottoPurchaseCounts counts) {
        try {
            return LottoMachine
                    .buyLotto(InputView.inputManualLottos(counts.getManualCount()), counts.getAutomaticCount());
        } catch (IllegalArgumentException e) {
            ErrorView.printErrorMessage(e);
            return buyLotto(counts);
        }
    }

    private static WinLotto createWinLotto() {
        try {
            return WinLotto.of(InputView.inputWinLotto(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException e) {
            ErrorView.printErrorMessage(e);
            return createWinLotto();
        }
    }
}
