package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.User;
import lotto.domain.RankStatistic;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        long money = InputView.inputMoney();
        int countOfManualLotto = InputView.inputCountOfManualLotto();
        User user = User.generate(money, countOfManualLotto, inputManualLottos(countOfManualLotto));
        OutputView.printLottos(user);
        OutputView.printLottoResult(user, new RankStatistic(user.getLottos(), inputWinningNumbers()));
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
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            input = InputView.inputLottoNumbers();
            retryFlag = validateInputLotto(input);
        }
        return Lotto.generateByManual(input);
    }

    private boolean validateInputLotto(String input) {
        try {
            Lotto.generateByManual(input);
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    private WinningNumbers inputWinningNumbers() {
        String winningLottoInput = "";
        String bonusNumberInput = "";
        boolean retryFlag = true;
        while (retryFlag) {
            winningLottoInput = InputView.inputWinningLotto();
            bonusNumberInput = InputView.inputBonusNumber();
            retryFlag = validateWinningNumbers(winningLottoInput, bonusNumberInput);
        }
        return WinningNumbers.generateByString(winningLottoInput, bonusNumberInput);
    }

    private boolean validateWinningNumbers(String winningLottoInput, String bonusNumberInput) {
        try {
            WinningNumbers.generateByString(winningLottoInput, bonusNumberInput);
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }
}
