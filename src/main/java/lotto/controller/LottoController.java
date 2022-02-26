package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.validator.NumberValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private PurchaseAmount purchaseAmount;

    public void run() {
        Lottos lottos = buyLotto();
        WinningLotto winningLotto = pickLastWeekWinningNumbers();
        calculateLottoResult(lottos, winningLotto);
    }

    private Lottos buyLotto() {
        OutputView.printPurchaseAmountRequest();
        try {
            purchaseAmount = new PurchaseAmount(InputView.inputPurchaseAmount());
            Lottos lottos = new Lottos(purchaseAmount);
            OutputView.printLottos(lottos);
            return lottos;
        } catch (IllegalArgumentException error) {
            OutputView.printErrorMessage(error.getMessage());
            return buyLotto();
        }
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

    private void calculateLottoResult(Lottos lottos, WinningLotto winningLotto) {
        OutputView.printStatisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottoResult.addMatchingCount(lottos, winningLotto);
        OutputView.printLottosResult(lottoResult);

        Profit profit = lottoResult.getProfit();
        double profitRate = profit.calculateProfitRate(purchaseAmount);
        OutputView.printProfitRate(profitRate);
    }
}
