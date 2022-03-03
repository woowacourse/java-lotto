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
        int countOfManualLotto = InputView.inputCountOfManualLotto(money);
        User user = User.generateWithManualLottos(money, countOfManualLotto, inputManualLottos(countOfManualLotto));
        OutputView.printLottos(user);
        OutputView.printLottoResult(user, new RankStatistic(user, inputWinningNumbers()));
    }

    private List<Lotto> inputManualLottos(int countOfManualLotto) {
        if (countOfManualLotto == 0) {
            return new ArrayList<>();
        }
        InputView.printInputLottoNumbersMessage();
        return IntStream.range(0, countOfManualLotto)
                .mapToObj(i -> inputLotto())
                .collect(Collectors.toList());
    }

    private Lotto inputLotto() {
        try {
            return Lotto.generateByManual(InputView.inputLottoNumbers());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputLotto();
        }
    }

    private WinningNumbers inputWinningNumbers() {
        try {
            return WinningNumbers.generateByString(
                    InputView.inputWinningLotto(),
                    InputView.inputBonusNumber()
            );
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputWinningNumbers();
        }
    }
}
