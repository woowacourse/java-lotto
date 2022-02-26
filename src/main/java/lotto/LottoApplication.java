package lotto;

import java.util.List;
import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.PurchaseLottoCounts;
import lotto.domain.WinLotto;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final Money money = createMoney();
        final PurchaseLottoCounts purchaseCounts = createPurchaseCounts(money);
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

    private static PurchaseLottoCounts createPurchaseCounts(final Money money) {
        try {
            return money.calculatePurchaseCounts(InputView.inputPurchaseManualCount());
        } catch (IllegalArgumentException e) {
            ErrorView.printErrorMessage(e);
            return createPurchaseCounts(money);
        }
    }

    private static Lottos buyLotto(final PurchaseLottoCounts counts) {
        try {
            final List<List<Integer>> manualLottos = InputView.inputManualLottos(counts.getManualCount());
            return LottoMachine.buyLotto(manualLottos, counts.getAutomaticCount());
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
