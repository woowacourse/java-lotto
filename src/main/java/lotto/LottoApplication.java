package lotto;

import lotto.domain.LottoPurchaseCounts;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.view.InputConvertor;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final Money money = InputConvertor.createMoney();
        final LottoPurchaseCounts lottoPurchaseCounts = money.calculatePurchaseCounts(InputView.inputPurchaseManualCount());
        OutputView.outputBuyLottoCounts(lottoPurchaseCounts.getAutomaticCount());

        final Lottos lottos = InputConvertor.createLottos(lottoPurchaseCounts.getAutomaticCount());
        OutputView.outputLottos(lottos.getLottos());

        final WinLotto winLotto = InputConvertor.createWinLotto();
        OutputView.outputResult(lottos.createResult(winLotto));
    }
}
