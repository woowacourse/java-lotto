package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;

    public void run() {
        Lottos lottos = buyLotto();
        WinningLotto winningLotto = pickLastWeekWinningNumbers();
        calculateLottoResult(lottos, winningLotto);
    }

    private Lottos buyLotto() {
        Money payment = payForLotto();
        int count = inputManualLottoCount(payment);
        List<Lotto> manualLottos = inputManualLottos(count);
        Lottos lottos = Lottos.of(manualLottos, payment);
        OutputView.printLottos(count, lottos.getAutoLottos(count));
        return lottos;
    }

    private Money payForLotto() {
        OutputView.printPaymentRequest();
        try {
            return new Money(InputView.inputPayment() / LOTTO_PRICE * LOTTO_PRICE);
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return payForLotto();
        }
    }

    private int inputManualLottoCount(Money payAmount) {
        OutputView.printManualLottoCountRequest();
        try {
            int count = InputView.inputManualLottoCount();
            payAmount.subtract(count * LOTTO_PRICE);
            return count;
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return inputManualLottoCount(payAmount);
        }
    }

    private List<Lotto> inputManualLottos(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        if (count == 0) {
            return lottoList;
        }
        OutputView.printManualLottoNumbersRequest();
        for (int i = 0; i < count; i++) {
            lottoList.add(pickLotto());
        }
        return lottoList;
    }

    private Lotto pickLotto() {
        try {
            List<Integer> lottoNumbers = InputView.inputLottoNumbers();
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return pickLotto();
        }
    }

    private WinningLotto pickLastWeekWinningNumbers() {
        OutputView.printWinningNumberRequest();
        Lotto winningLotto = pickLotto();
        return combineBonusBall(winningLotto);
    }

    private WinningLotto combineBonusBall(Lotto lotto) {
        OutputView.printBonusBallRequest();
        try {
            LottoNumber bonusBall = new LottoNumber(InputView.inputBonusBall());
            return new WinningLotto(lotto, bonusBall);
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return combineBonusBall(lotto);
        }
    }

    private void calculateLottoResult(Lottos lottos, WinningLotto winningLotto) {
        OutputView.printStatisticsTitle();
        LottoResult lottoResult = new LottoResult();
        lottoResult.addMatchingCount(lottos, winningLotto);
        OutputView.printLottosResult(lottoResult.getLottoResult());

        Money payment = new Money(lottos.getLottos().size() * LOTTO_PRICE);
        double profitRate = lottoResult.calculateProfitRate(payment);
        OutputView.printProfitRate(profitRate);
    }
}
