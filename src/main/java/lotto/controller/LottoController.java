package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Payment;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.controller.Creator.*;
import static lotto.view.Output.*;

public class LottoController {

    private LottoController() {}

    public static void run() {
        printRequestPayment();
        Payment payment = createPayment();

        Lottos lottos = buyLottos(payment);

        WinningLotto winningLotto = getWinningLotto();
        showResult(lottos, payment, winningLotto);
    }

    private static Lottos buyLottos(final Payment payment) {
        printRequestManualCount();
        LottoCount lottoCount = createLottoCount(payment);
        Lottos lottos = getLottos(lottoCount);

        printLottoCount(lottoCount);
        printLottos(lottos);

        return lottos;
    }

    private static Lottos getLottos(final LottoCount lottoCount) {
        if (lottoCount.getManualCount() > 0) {
            printRequestManualLottos();
        }
        Lottos manualLottos = createManualLottos(lottoCount);
        Lottos autoLottos = createAutoLottos(lottoCount);

        return new Lottos(getTotalLottos(manualLottos, autoLottos));
    }

    private static List<Lotto> getTotalLottos(final Lottos manualLottos, final Lottos autoLottos) {
        List<Lotto> totalLottos = new ArrayList<>();
        totalLottos.addAll(manualLottos.getLottos());
        totalLottos.addAll(autoLottos.getLottos());
        return totalLottos;
    }

    private static WinningLotto getWinningLotto() {
        printRequestWinNumber();
        Lotto winningNumbers = createLottoNumbers();
        printRequestBonusBall();
        return createWinningLotto(winningNumbers);
    }

    private static void showResult(final Lottos lottos, final Payment payment, final WinningLotto winningLotto) {
        printStatisticsTitle();

        LottoResult lottoResult = createLottoResult();
        winningLotto.match(lottos, lottoResult);
        printLottoResult(lottoResult);

        showProfitRate(payment, lottoResult);
    }

    private static void showProfitRate(final Payment payment, final LottoResult lottoResult) {
        double profitRate = lottoResult.calculateRate(payment);
        printProfitRate(profitRate);
    }
}
