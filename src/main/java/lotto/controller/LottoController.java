package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.domain.Payment;
import lotto.domain.WinningLotto;
import lotto.view.Entering;
import lotto.view.KeyEnter;

import static lotto.view.Input.*;
import static lotto.view.Input.inputWinNumber;
import static lotto.view.Output.*;

public class LottoController {
    private static final Entering entering = new KeyEnter();

    public static void run() {
        printRequestPayment();
        Payment payment = createPayment();

        Lottos lottos = buyLotto(payment);

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

    private static Lottos buyLotto(Payment payment) {
        int lottoCount = payment.getLottoCount();
        Lottos lottos = new Lottos(lottoCount);

        printLottoCount(lottoCount);
        printLottos(lottos);

        return lottos;
    }

    private static WinningLotto getWinningLotto() {
        printRequestWinNumber();
        Lotto winLotto = createWinNumber();
        printRequestBonusBall();
        return createWinningLotto(winLotto);
    }

    private static Lotto createWinNumber() {
        try {
            return new Lotto(inputWinNumber(entering));
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinNumber();
        }
    }

    private static WinningLotto createWinningLotto(Lotto winLotto) {
        try {
            Ball bonusBall = new Ball(inputBonusBall(entering));
            return new WinningLotto(winLotto, bonusBall);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinningLotto(winLotto);
        }
    }

    private static void showResult(Lottos lottos, Payment payment, WinningLotto winningLotto) {
        printStatisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottoResult.match(lottos, winningLotto);
        printLottoResult(lottoResult);

        showProfitRate(payment, lottoResult);
    }

    private static void showProfitRate(Payment payment, LottoResult lottoResult) {
        Profit profit = new Profit();
        double profitRate = profit.calculateRate(lottoResult.getTotalMoney(), payment);
        printProfitRate(profitRate);
    }
}
