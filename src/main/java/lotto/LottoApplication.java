package lotto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
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
        final LottoPurchaseCounts lottoPurchaseCounts = money
                .calculatePurchaseCounts(InputView.inputPurchaseManualCount());
        OutputView.outputBuyLottoCounts(lottoPurchaseCounts);

        final List<Lotto> manualLottos = InputView.inputManualLottos(lottoPurchaseCounts.getManualCount())
                .stream()
                .map(Lotto::from)
                .collect(Collectors.toList());

        final Lottos lottos = RandomLottoMachine.buyLotto(manualLottos, lottoPurchaseCounts.getAutomaticCount());
        OutputView.outputLottos(lottos.getLottos());
        final WinLotto winLotto = new WinLotto(Lotto.from(InputView.inputWinLotto()),
                LottoNumber.valueOf(InputView.inputBonusNumber()));
        OutputView.outputResult(lottos.createResult(winLotto));
    }
}
