package lotto;

import lotto.View.InputView;
import lotto.View.OutputView;
import lotto.domain.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Money money = MoneyParser.parseMoney(InputView.inputMoney());
        int countOfManualLotto = InputView.inputCountOfManualLotto();

        UserLottos manualLottos = ManualLottoParser.parseManualLottoNumbers(InputView.inputManualLotto(countOfManualLotto));
        UserLottos autoLottos = AutoLottoGenerator.generateAutoLottos(money.getAllLottoSize() - countOfManualLotto);

        OutputView.printCountOfLotto(money, countOfManualLotto);
        OutputView.printUserLottos(manualLottos);
        OutputView.printUserLottos(autoLottos);

        String scannedWinningLotto = InputView.inputWinningLotto();
        String scannedBonusBall = InputView.inputBonusNumber();
        WinningLotto winningLotto = WinningLottoParser.parseWinningLotto(scannedWinningLotto, scannedBonusBall);

        List<Lotto> allUserLottos=manualLottos.getUserLottos();
        allUserLottos.addAll(autoLottos.getUserLottos());
        LottoGame lottoGame = new LottoGame(new UserLottos(allUserLottos), winningLotto);
        OutputView.printCountOfRank(lottoGame.getCountOfRank());
        OutputView.printRateOfReturn(lottoGame.calculateRateOfReturn());
    }
}
