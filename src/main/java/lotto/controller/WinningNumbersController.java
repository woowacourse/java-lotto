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

    private static final String DUPLICATE_NUMBER_MESSAGE = "중복입니다.";

    public WinningNumbers getWinningNumbers() {
        final Lotto winningLotto = getWinningLotto();
        final Number bonusNumber = getBonusNumber(winningLotto);
        return new WinningNumbers(winningLotto, bonusNumber);
    }

    private Lotto getWinningLotto() {
        Optional<Lotto> winningLotto;

        do {
            List<Integer> integers = StringUtil.toIntegers(InputView.inputWinningNumbers());
            winningLotto = inputWinningLotto(integers);
        } while (winningLotto.isEmpty());

        return winningLotto.get();
    }

    private Optional<Lotto> inputWinningLotto(List<Integer> integers) {
        try {
            return Optional.of(new Lotto(integers));
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }

    private Number getBonusNumber(Lotto winningLotto) {
        Optional<Number> bonusNumber;

        do {
            bonusNumber = inputBonusNumber(winningLotto);
        } while (bonusNumber.isEmpty());

        return bonusNumber.get();
    }

    private Optional<Number> inputBonusNumber(Lotto winningLotto) {
        try {
            final Number bonusNumber = Number.getInstance(InputView.inputBonusNumber());
            if (winningLotto.contains(bonusNumber)) {
                throw new IllegalArgumentException(DUPLICATE_NUMBER_MESSAGE);
            }
            return Optional.of(bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }
}
