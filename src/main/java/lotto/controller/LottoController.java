package lotto.controller;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;

public class LottoController {

    private final InputController inputController;

    public LottoController() {
        inputController = new InputController();
    }

    public void run() {
        Lottos lottos = makeLottos();
        ResultView.printResult(lottos);

        WinningLotto winningLotto = makeWinningLotto();

        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }

    private Lottos makeLottos() {
        int countLotto = inputController.countLotto(InputView.inputPrice());
        Lottos lottos = new Lottos();
        insertLottoToLottos(countLotto, lottos);
        return lottos;
    }

    private void insertLottoToLottos(int countLotto, Lottos lottos) {
        for (int i = 0; i < countLotto; i++) {
            Lotto lotto = RandomLottoGenerator.generate();
            lottos.insert(lotto);
        }
    }

    private WinningLotto makeWinningLotto() {
        List<Integer> winningNumbers = inputController.splitWinningNumbers(InputView.inputWinningNumbers());
        int bonusNumber = inputController.toIntBonusNumber(InputView.inputBonusNumber(), winningNumbers);
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
