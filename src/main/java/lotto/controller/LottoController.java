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

import static lotto.view.Input.*;
import static lotto.view.Output.*;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;
    private static final String DELIMITER = ",";

    public static void run() {
        printRequestPayment();
        Payment payment = createPayment();

        Lottos lottos = buyLotto(payment);

        showLottoResult(lottos, payment);
    }

    private static Payment createPayment() {
        try {
            return new Payment(inputPayment());
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createPayment();
        }
    }

    private static Lottos buyLotto(Payment payment) {
        int lottoCount = getLottoCount(payment);
        Lottos lottos = new Lottos(lottoCount);

        printLottoCount(lottoCount);
        printLottos(lottos);

        return lottos;
    }

    private static int getLottoCount(Payment payment) {
        return payment.getPayment() / LOTTO_PRICE;
    }

    private static void showLottoResult(Lottos lottos, Payment payment) {
        requestWinNumber();
        Lotto winLotto = createWinNumber();
        requestBonusBall();
        WinningLotto winningLotto = createWinningLotto(winLotto);

        showResult(lottos, payment, winningLotto);
    }

    private static Lotto createWinNumber() {
        try {
            List<String> numbers = Arrays.stream(inputWinNumber().split(DELIMITER))
                    .map(String::trim)
                    .collect(Collectors.toList());
            return new Lotto(numbers);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinNumber();
        }
    }

    private static WinningLotto createWinningLotto(Lotto winLotto) {
        try {
            Ball bonusBall = new Ball(inputBonusBall());
            return new WinningLotto(winLotto, bonusBall);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinningLotto(winLotto);
        }
    }

    private static void showResult(Lottos lottos, Payment payment, WinningLotto winningLotto) {
        printStatisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottos.addMatchingCount(lottoResult, winningLotto);
        printLottoResult(lottoResult);

        Profit profit = new Profit();
        double profitRate = profit.calculateRate(lottoResult.getTotalMoney(), payment);
        printProfitRate(profitRate);
    }
}
