package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.CountOfLotto;
import lotto.domain.Lotto;
import lotto.domain.LottosFactory;
import lotto.domain.Money;
import lotto.domain.RankStatistic;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        Money money = inputMoney();
        CountOfLotto countOfLotto = inputCountOfLotto(money);
        List<Lotto> lottos = LottosFactory.generate(
                inputManualLottos(countOfLotto.getCountOfManualLotto()),
                countOfLotto.getCountOfAutoLotto()
        );
        OutputView.printLottos(lottos, countOfLotto);
        OutputView.printLottoResult(new RankStatistic(lottos, inputWinningNumbers()), money);
    }

    private Money inputMoney() {
        long input = 0;
        boolean retryFlag = true;
        while (retryFlag) {
            input = InputView.inputMoney();
            retryFlag = validateInputMoney(input);
        }
        return new Money(input);
    }

    private boolean validateInputMoney(long input) {
        try {
            new Money(input);
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    private CountOfLotto inputCountOfLotto(Money money) {
        int input = 0;
        boolean retryFlag = true;
        while (retryFlag) {
            input = InputView.inputCountOfLotto();
            retryFlag = validateCountOfLotto(input, money);
        }
        return new CountOfLotto(input, money);
    }

    private boolean validateCountOfLotto(int input, Money money) {
        try {
            new CountOfLotto(input, money);
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    private List<Lotto> inputManualLottos(int countOfManualLotto) {
        if (countOfManualLotto == 0) {
            return new ArrayList<>();
        }
        OutputView.printInputLottoNumbersMessage();
        return IntStream.range(0, countOfManualLotto)
                .mapToObj(i -> inputLotto())
                .collect(Collectors.toList());
    }

    private Lotto inputLotto() {
        List<Integer> input = new ArrayList<>();
        boolean retryFlag = true;
        while (retryFlag) {
            input = InputView.inputLottoNumbers();
            retryFlag = validateInputLotto(input);
        }
        return Lotto.generateByManual(input);
    }

    private boolean validateInputLotto(List<Integer> input) {
        try {
            Lotto.generateByManual(input);
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    private WinningNumbers inputWinningNumbers() {
        List<Integer> winningLottoInput = new ArrayList<>();
        int bonusNumberInput = 0;
        boolean retryFlag = true;
        while (retryFlag) {
            winningLottoInput = InputView.inputWinningLotto();
            bonusNumberInput = InputView.inputBonusNumber();
            retryFlag = validateWinningNumbers(winningLottoInput, bonusNumberInput);
        }
        return WinningNumbers.generate(winningLottoInput, bonusNumberInput);
    }

    private boolean validateWinningNumbers(List<Integer> winningLottoInput, int bonusNumberInput) {
        try {
            WinningNumbers.generate(winningLottoInput, bonusNumberInput);
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }
}
