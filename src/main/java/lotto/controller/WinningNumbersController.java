package lotto.controller;

import java.util.List;
import java.util.Optional;
import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.domain.WinningNumbers;
import lotto.utils.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class WinningNumbersController {

    public WinningNumbers getWinningNumbers() {
        final Lotto winningLotto = getWinningLotto();
        Optional<WinningNumbers> winningNumbers;

        do {
            winningNumbers = getBonusNumber(winningLotto);
        } while (winningNumbers.isEmpty());
        return winningNumbers.get();
    }

    private Lotto getWinningLotto() {
        Optional<Lotto> winningLotto;

        do {
            List<Integer> integers = StringUtil.toIntegers(InputView.inputWinningNumbers());
            winningLotto = getValidWinningLotto(integers);
        } while (winningLotto.isEmpty());

        return winningLotto.get();
    }

    private Optional<Lotto> getValidWinningLotto(List<Integer> integers) {
        try {
            return Optional.of(new Lotto(integers));
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }

    private Optional<WinningNumbers> getBonusNumber(Lotto winningLotto) {
        try {
            final Number bonusNumber = Number.getInstance(InputView.inputBonusNumber());
            return Optional.of(new WinningNumbers(winningLotto, bonusNumber));
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }
}
