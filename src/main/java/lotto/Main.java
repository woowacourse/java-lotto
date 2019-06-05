package lotto;

import lotto.View.InputView;
import lotto.View.OutputView;
import lotto.domain.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Money money = new Money(Integer.parseInt(InputView.inputMoney()));
        int countOfManualLotto = InputView.inputCountOfManualLotto();

        User user = new User(money, countOfManualLotto);

        if (countOfManualLotto != 0) {
            List<Lotto> manualLottos = ManualLottoParser.parseManualLottoNumbers(InputView.inputManualLotto(countOfManualLotto));
            user.addManualLottos(manualLottos);
        }

        OutputView.printCountOfLotto(money, countOfManualLotto);
        OutputView.printUserLottos(user.getUserLottos());

        String[] scannedWinningLotto = InputView.inputWinningLotto();
        String scannedBonusBall = InputView.inputBonusNumber();
        WinningLotto winningLotto = WinningLottoParser.parseWinningLotto(scannedWinningLotto, scannedBonusBall);

        LottoGame lottoGame = new LottoGame(user, winningLotto);
        OutputView.printCountOfRank(lottoGame.getCountOfRank());
        OutputView.printRateOfReturn(lottoGame.calculateRateOfReturn());
    }
}
