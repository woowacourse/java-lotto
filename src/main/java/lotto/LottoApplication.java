package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseCounts;
import lotto.domain.Money;
import lotto.domain.RandomLottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final Money money = new Money(InputView.inputMoney());
        final LottoPurchaseCounts lottoPurchaseCounts = money
                .calculatePurchaseCounts(InputView.inputPurchaseManualCount());
        OutputView.outputBuyLottoCounts(lottoPurchaseCounts);

        final List<List<Integer>> manualLottos = InputView.inputManualLottos(lottoPurchaseCounts.getManualCount());
        final List<Lotto> automaticLottos = IntStream.range(0, lottoPurchaseCounts.getAutomaticCount())
                .mapToObj(index -> RandomLottoMachine.createRandomLotto())
                .collect(Collectors.toList());

//        final Lottos lottos = InputConvertor.createLottos(lottoPurchaseCounts.getAutomaticCount());
//        OutputView.outputLottos(lottos.getLottos());
//
//        final WinLotto winLotto = InputConvertor.createWinLotto();
//        OutputView.outputResult(lottos.createResult(winLotto));
    }
}
