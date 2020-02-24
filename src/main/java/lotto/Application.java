package lotto;

import lotto.controller.GameController;
import lotto.domain.*;
import lotto.generator.LottoGenerateManager;
import lotto.generator.LottoRandomGenerator;
import lotto.generator.LottoSelectedGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String args[]) {
        Lottos lottos = initUserLottos();
        WinningLotto winningLotto = initWinnerLotto();

        ResultsDTO resultsDTO = GameController.run(lottos, winningLotto);

        OutputView.printResults(resultsDTO);
    }

    private static Lottos initUserLottos() {
        PaidPrice paidPrice = new PaidPrice(InputView.getPayment());
        OutputView.printLottoCount(paidPrice);

        Lottos lottos = LottoGenerateManager.createLottos(paidPrice, new LottoRandomGenerator());
        OutputView.printLottosNumbers(lottos);
        return lottos;
    }

    private static WinningLotto initWinnerLotto() {
        String winningNumber = InputView.getWinningLottoNumbers();
        String bonusNumber = InputView.getBonusNumber();
        WinningLotto winningLotto =
                LottoGenerateManager.createWinningLotto(new LottoSelectedGenerator(winningNumber), bonusNumber);
        return winningLotto;
    }
}
