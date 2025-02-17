package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

import static lotto.domain.Lotto.validateLottoNumber;

public class LottoController {
    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = LottoMachine.issueLottos(purchaseAmount.calculateLottoAmount());
        OutputView.printLottos(lottos);
        WinningNumbers winningNumbers = getWinningNumbers();
        BonusNumber bonusNumber = getBonusNumber(winningNumbers);
        WinningStatistics winningStatistics = LottoMachine.calculateStatistics(lottos, winningNumbers, bonusNumber);
        double returnRate = winningStatistics.calculateReturnRate(purchaseAmount.calculateLottoAmount());
        OutputView.printWinningStatistics(winningStatistics, returnRate);
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            int purchaseAmount = InputView.inputPurchaseAmount();
            return new PurchaseAmount(purchaseAmount);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private WinningNumbers getWinningNumbers() {
        try {
            List<Integer> winningNumbers = InputView.inputWinningNumbers();
            return new WinningNumbers(winningNumbers);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private BonusNumber getBonusNumber(final WinningNumbers winningNumbers) {
        try {
            int bonusNumber = InputView.inputBonusNumber();
            validateLottoNumber(bonusNumber);
            winningNumbers.validateBonusNumberDuplicated(bonusNumber);
            return new BonusNumber(bonusNumber);
        } catch (final IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getBonusNumber(winningNumbers);
        }
    }
}
