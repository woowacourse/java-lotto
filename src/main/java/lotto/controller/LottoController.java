package lotto.controller;

import java.util.List;
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
        Lottos lottos = makeLottos();
        ResultView.printResult(lottos);

        WinningLotto winningLotto = makeWinningLotto();

        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }

    private Lottos makeLottos() throws RuntimeException {
        int countLotto = inputController.countLotto(InputView.inputPrice());
        Lottos lottos = new Lottos(countLotto);
        return lottos;
    }

//    private void insertLottoToLottos(int countLotto, Lottos lottos) {
//        for (int i = 0; i < countLotto; i++) {
//            Lotto lotto = RandomLottoGenerator.generate();
//            lottos.insert(lotto);
//        }
//    }

    private WinningLotto makeWinningLotto() throws RuntimeException {
        List<Integer> winningNumbers = inputController.splitWinningNumbers(InputView.inputWinningNumbers());
        int bonusNumber = inputController.toIntBonusNumber(InputView.inputBonusNumber(), winningNumbers);
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
