package lotto;

import lotto.View.InputView;
import lotto.View.OutputView;
import lotto.domain.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Money money = new Money(Integer.parseInt(InputView.inputMoney()));
        OutputView.printCountOfLotto(money);

        User user = new User(money);
        OutputView.printUserLottos(user.getUserLottos());

        List<LottoNumber> winningLottoNumbers = WinningLottoParser.parseLottoNumbers(InputView.inputWinningLotto());
        LottoNumber bonusBall = WinningLottoParser.parseBonusBall(InputView.inputBonusNumber());

        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, bonusBall);

        LottoGame lottoGame = new LottoGame(user, winningLotto);
        OutputView.printCountOfRank(lottoGame.getCountOfRank());
        OutputView.printRateOfReturn(lottoGame.calculateRateOfReturn());
    }
}
