package lotto;

import lotto.View.InputView;
import lotto.View.OutputView;
import lotto.domain.*;

public class Main {
    public static void main(String[] args) {
        String scannedMoney = InputView.inputMoney();
        Money money = new Money(Integer.parseInt(scannedMoney));
        OutputView.printCountOfLotto(money);

        User user = new User(money);
        OutputView.printUserLottos(user.getUserLottos());

        String[] scannedWinningLotto = InputView.inputWinningLotto();
        WinningLotto winningLotto = new WinningLotto(WinningLottoParser.parseLottoNumberList(scannedWinningLotto));

        LottoGame lottoGame=new LottoGame(user,winningLotto);
        OutputView.printCountOfRank(lottoGame.getCountOfRank());
        OutputView.printRateOfReturn(lottoGame.calculateRateOfReturn());
    }
}
