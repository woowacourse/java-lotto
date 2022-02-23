package lotto.controller;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    private final InputController inputController;

    public LottoController() {
        inputController = new InputController();
    }

    public void run() {
        int countLotto = inputController.countLotto(InputView.inputPrice());
        Lottos lottos = new Lottos();
        insertLottoToLottos(countLotto, lottos);
        ResultView.printResult(lottos);

        WinningLotto winningLotto = new WinningLotto(
                inputController.splitWinningNumbers(InputView.inputWinningNumbers()),
                inputController.toIntBonusNumber(InputView.inputBonusNumber())
        );
    }

    public void insertLottoToLottos(int countLotto, Lottos lottos) {
        for (int i = 0; i < countLotto; i++) {
            Lotto lotto = RandomLottoGenerator.generate();
            lottos.insert(lotto);
        }
    }
}
