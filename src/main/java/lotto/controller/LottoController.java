package lotto.controller;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.domain.Payment;
import lotto.domain.WinningLotto;
import lotto.view.Entering;
import lotto.view.KeyEnter;

import java.util.ArrayList;
import java.util.List;

import static lotto.view.Input.*;
import static lotto.view.Input.inputWinningNumbers;
import static lotto.view.Output.*;

public class LottoController {
    private static final Entering entering = new KeyEnter();

    private LottoController() {}

    public static void run() {
        printRequestPayment();
        Payment payment = createPayment();

        Lottos lottos = buyLottos(payment);

        WinningLotto winningLotto = getWinningLotto();
        showResult(lottos, payment, winningLotto);
    }

    private static Payment createPayment() {
        try {
            return new Payment(inputPayment(entering));
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createPayment();
        }
    }

    private static Lottos buyLottos(final Payment payment) {
        int lottoCount = payment.getLottoCount();
        Lottos lottos = createLottos(lottoCount);

        printLottoCount(lottoCount);
        printLottos(lottos);

        return lottos;
    }

    private static Lottos createLottos(final int lottoCount) {
        List<Lotto> lottos = new ArrayList<>(lottoCount);
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(Lotto.selectRandomNumbers()));
        }
        return new Lottos(lottos);
    }

    private static WinningLotto getWinningLotto() {
        printRequestWinNumber();
        Lotto winningNumbers = createWinningNumbers();
        printRequestBonusBall();
        return createWinningLotto(winningNumbers);
    }

    private static Lotto createWinningNumbers() {
        try {
            return new Lotto(inputWinningNumbers(entering));
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinningNumbers();
        }
    }

    private static WinningLotto createWinningLotto(final Lotto winningNumbers) {
        try {
            Ball bonusBall = new Ball(inputBonusBall(entering));
            return new WinningLotto(winningNumbers, bonusBall);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinningLotto(winningNumbers);
        }
    }

    private static void showResult(final Lottos lottos, final Payment payment, final WinningLotto winningLotto) {
        printStatisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottoResult.match(lottos, winningLotto);
        printLottoResult(lottoResult);

        showProfitRate(payment, lottoResult);
    }

    private static void showProfitRate(final Payment payment, final LottoResult lottoResult) {
        Profit profit = new Profit();
        double profitRate = profit.calculateRate(lottoResult.getTotalMoney(), payment);
        printProfitRate(profitRate);
    }
}
