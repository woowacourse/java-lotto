package lotto.controller;

import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    private final InputController inputController;

    public LottoController() {
        inputController = new InputController();
    }

    public void run() throws RuntimeException {
        Lottos lottos = inputController.makeLottos(InputView.inputPrice());
        ResultView.printResult(lottos);

        WinningLotto winningLotto = inputController.makeWinningLotto(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }
}
