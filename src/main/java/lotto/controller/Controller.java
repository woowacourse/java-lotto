package lotto.controller;

import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.User;
import lotto.domain.RankStatistic;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        User user = generateUserByString();
        buyLottosByManual(user, InputView.inputCountForBuy());
        buyAllLottosByAuto(user);
        OutputView.printLottos(user);
        OutputView.printLottoResult(user, new RankStatistic(user, inputWinningNumbers()));
    }

    private User generateUserByString() {
        try {
            return User.generateUserByString(InputView.inputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return generateUserByString();
        }
    }

    private void buyLottosByManual(User user, int countForBuy) {
        if (countForBuy == 0) {
            return;
        }
        try {
            InputView.printInputLottoNumbersMessage();
            IntStream.range(0, countForBuy).forEach(integer -> user.buyLottoByManual(inputLotto()));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
        }
    }

    private Lotto inputLotto() {
        try {
            return Lotto.generateLottoByString(InputView.inputLottoNumbers());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputLotto();
        }
    }

    private void buyAllLottosByAuto(User user) {
        try {
            user.buyAllLottosByAuto();
        } catch (IllegalArgumentException exception) {
        }
    }

    private WinningNumbers inputWinningNumbers() {
        try {
            return WinningNumbers.generateWinningNumbersByString(
                    InputView.inputWinningLotto(),
                    InputView.inputBonusNumber()
            );
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputWinningNumbers();
        }
    }
}
