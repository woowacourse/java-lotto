package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.validator.NumberValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;

    public void run() {
        Money purchaseAmount = payForLotto();
        Lottos lottos = buyLotto(purchaseAmount);
        WinningLotto winningLotto = pickLastWeekWinningNumbers();
        calculateLottoResult(purchaseAmount, lottos, winningLotto);
    }

    private Money payForLotto() {
        OutputView.printPurchaseAmountRequest();
        try {
            return new Money(convertPurchaseAmount(InputView.inputPurchaseAmount()));
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return payForLotto();
        }
    }

    private int convertPurchaseAmount(String purchaseAmount) {
        NumberValidator.validateNumber(purchaseAmount);
        return Integer.parseInt(purchaseAmount) / LOTTO_PRICE * LOTTO_PRICE;
    }

    private Lottos buyLotto(Money purchaseAmount) {
        Lottos lottos = new Lottos(purchaseAmount);
        OutputView.printLottos(lottos);
        return lottos;
    }

    private WinningLotto pickLastWeekWinningNumbers() {
        Lotto lotto = pickLotto();
        return combineBonusBall(lotto);
    }

    private Lotto pickLotto() {
        OutputView.printWinningNumberRequest();
        try {
            return new Lotto(convertLottoNumbers(InputView.inputWinningNumber()));
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return pickLotto();
        }
    }

    private List<Integer> convertLottoNumbers(final List<String> lottoNumbers) {
        for (String lottoNumber : lottoNumbers) {
            NumberValidator.validateNumber(lottoNumber);
        }

        return lottoNumbers.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private WinningLotto combineBonusBall(Lotto lotto) {
        OutputView.printBonusBallRequest();
        try {
            LottoNumber bonusBall = new LottoNumber(convertBonusBall(InputView.inputBonusBall()));
            return new WinningLotto(lotto, bonusBall);
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return combineBonusBall(lotto);
        }
    }

    private int convertBonusBall(String bonusBallNumber) {
        NumberValidator.validateNumber(bonusBallNumber);
        return Integer.parseInt(bonusBallNumber);
    }

    private void calculateLottoResult(Money purchaseAmount, Lottos lottos, WinningLotto winningLotto) {
        OutputView.printStatisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottoResult.addMatchingCount(lottos, winningLotto);
        OutputView.printLottosResult(lottoResult);

        Profit profit = lottoResult.getProfit();
        double profitRate = profit.calculateProfitRate(purchaseAmount);
        OutputView.printProfitRate(profitRate);
    }
}
