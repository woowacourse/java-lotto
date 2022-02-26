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
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private PurchaseAmount purchaseAmount;

    public void run() {
        Lottos lottos = buyLotto();
        WinningLotto winningLotto = pickLastWeekWinningNumbers();
        calculateLottoResult(lottos, winningLotto);
    }

    private Lottos buyLotto() {
        Output.askPurchaseAmount();
        try {
            purchaseAmount = new PurchaseAmount(Input.purchaseAmount());
            Lottos lottos = new Lottos(purchaseAmount);
            Output.lottos(lottos);
            return lottos;
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return buyLotto();
        }
    }

    private WinningLotto pickLastWeekWinningNumbers() {
        Lotto lotto = pickLotto();
        return combineBonusBall(lotto);
    }

    private Lotto pickLotto() {
        Output.askWinNumber();
        try {
            return new Lotto(convertLottoNumbers(Input.winNumber()));
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
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
        Output.askBonusBall();
        try {
            LottoNumber bonusBall = new LottoNumber(convertBonusBall(Input.bonusBall()));
            return new WinningLotto(lotto, bonusBall);
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return combineBonusBall(lotto);
        }
    }

    private int convertBonusBall(String bonusBallNumber) {
        NumberValidator.validateNumber(bonusBallNumber);
        return Integer.parseInt(bonusBallNumber);
    }

    private void calculateLottoResult(Lottos lottos, WinningLotto winningLotto) {
        Output.statisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottoResult.addMatchingCount(lottos, winningLotto);
        Output.lottoResult(lottoResult);

        Profit profit = new Profit();
        double profitRate = profit.calculate(lottoResult.getTotalMoney(), purchaseAmount);
        Output.profitRate(profitRate);
    }
}
