package lotto.controller;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.InputValidator;
import lotto.util.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        int amount = inputAmount();
        Lottos lottos = makeLottos(calculateLottoCount(amount));
        ResultView.printResult(lottos);

        WinningLotto winningLotto = makeWinningLotto();

        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }

    private int inputAmount() {
        try {
            return InputValidator.validatePurchaseAmount(InputView.inputPurchaseAmount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputAmount();
        }
    }

    private Lottos makeLottos(int lottoCount) {
        Lottos lottos = new Lottos();
        insertLottoToLottos(lottoCount, lottos);
        return lottos;
    }

    private int calculateLottoCount(int amount) {
        return amount / Lotto.LOTTO_PRICE;
    }

    private void insertLottoToLottos(int countLotto, Lottos lottos) {
        for (int i = 0; i < countLotto; i++) {
            Lotto lotto = RandomLottoGenerator.generate();
            lottos.insert(lotto);
        }
    }

    private WinningLotto makeWinningLotto() {
        try {
            return new WinningLotto(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return makeWinningLotto();
        }
    }
}
