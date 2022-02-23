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
        int countLotto = inputController.countLotto(InputView.inputPrice());
        Lottos lottos = new Lottos();
        insertLottoToLottos(countLotto, lottos);
        ResultView.printResult(lottos);

        List<Integer> winningNumbers = inputController.splitWinningNumbers(InputView.inputWinningNumbers());
        int bonusNumber = inputController.toIntBonusNumber(InputView.inputBonusNumber(), winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        winningLotto.checkRank(lottos);
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getRank());
        }
        lottos.countRank();
    }

    public void insertLottoToLottos(int countLotto, Lottos lottos) {
        for (int i = 0; i < countLotto; i++) {
            Lotto lotto = RandomLottoGenerator.generate();
            lottos.insert(lotto);
        }
    }

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.run();
    }
}
