package lotto;

import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        UserLottos userLottos = new UserLottos(InputView.inputLottoMoney());
        OutputView.printLottos(userLottos);
        WinningLotto winningLotto = new WinningLotto(InputView.inputWinningLotto());
        userLottos.match(winningLotto);
        OutputView.printResult(userLottos);
    }
}
