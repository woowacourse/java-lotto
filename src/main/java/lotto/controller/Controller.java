package lotto.controller;

import lotto.InputValidator;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Controller {
    public static void main(String[] args) {
        String priceInput = InputView.promptPrice();

        Money money = createMoney(priceInput);
        Lottoes lottoes = LottoFactory.createAutoLottoes(money.getSize());
        OutputView.printLottoes(lottoes);

        List<String> winningNumberInput = InputView.promptWinningNumber();
        Lotto winnigLottoNumbers = createWinningLottoNumbers(winningNumberInput);
        String bonusBall = InputView.promptBonusBall();
        WinningLotto winningLotto = createWinningLotto(winnigLottoNumbers, bonusBall);

        Statistics statistics = StatisticsFactory.createStatistics();
        statistics.calculateResult(lottoes,winningLotto);
        OutputView.printStatistics(statistics);
    }

    private static WinningLotto createWinningLotto(Lotto winnigLottoNumbers, String bonusBall) {
        while (InputValidator.isNotValidWinningLotto(winnigLottoNumbers, bonusBall)) {
            bonusBall = InputView.promptBonusBall();
        }
        return LottoFactory.createWinningLotto(winnigLottoNumbers, Integer.parseInt(bonusBall));
    }

    private static Lotto createWinningLottoNumbers(List<String> winningNumberInput) {
        while (InputValidator.isNotValidLotto(winningNumberInput)) {
            winningNumberInput = InputView.promptWinningNumber();
        }
        return LottoFactory.createLotto(winningNumberInput);
    }


    private static Money createMoney(String moneyInput) {
        while (InputValidator.isNotValidPrice(moneyInput)) {
            moneyInput = InputView.promptPrice();
        }
        Money money = MoneyFactory.createMoney(Integer.parseInt(moneyInput));
        return money;
    }


}
