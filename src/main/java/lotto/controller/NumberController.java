package lotto.controller;

import java.util.List;
import java.util.Optional;
import lotto.domain.WinningNumbers;
import lotto.utils.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class NumberController {

    public WinningNumbers getWinningNumbers() {
        Optional<WinningNumbers> winningNumbers;

        do {
            List<Integer> integers = StringUtil.toIntegers(InputView.inputWinningNumbers());
            winningNumbers = getWinNumbs(integers);
        } while (winningNumbers.isEmpty());
        return winningNumbers.get();
    }

    private Optional<WinningNumbers> getWinNumbs(List<Integer> integers) {
        try {
            return Optional.of(new WinningNumbers(integers));
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }

    public void getBonusNumber(WinningNumbers numbers) {
        boolean isValid;

        do {
            isValid = validateBonusNumber(numbers);
        } while (!isValid);
    }

    private boolean validateBonusNumber(WinningNumbers numbers) {
        try {
            numbers.initBonusNumber(InputView.inputBonusNumber());
            return true;
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return false;
        }
    }
}
