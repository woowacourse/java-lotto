package lotto;

import lotto.controller.GameController;
import lotto.domain.*;
import lotto.generator.LottoGenerateManager;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;

public class Application {
    public static void main(String args[]) {
        Lottos lottos = initUserLottos();
        WinningLotto winningLotto = initWinnerLotto();

        ResultsDTO resultsDTO = GameController.run(lottos, winningLotto);

        OutputView.printResults(resultsDTO);
    }

    private static Lottos initUserLottos() {
        PaidPrice paidPrice = new PaidPrice(InputView.getPayment());
        LottoSize manualLottoSize = new LottoSize(paidPrice, InputView.getManualLottoSize());
        LottoSize autoLottoSize = new LottoSize(paidPrice,
                paidPrice.getTotalLottoCount() - manualLottoSize.getLottoSize());

        ArrayList<String> manualLottoNumbers = InputView.getManualLottoNumbers(manualLottoSize);
        Lottos lottos = LottoGenerateManager.createLottos(manualLottoNumbers, autoLottoSize);
        OutputView.printLottoSize(manualLottoSize, autoLottoSize);
        OutputView.printLottosNumbers(lottos);
        return lottos;
    }

    private static WinningLotto initWinnerLotto() {
        String winningNumber = InputView.getWinningLottoNumbers();
        String bonusNumber = InputView.getBonusNumber();
        WinningLotto winningLotto =
                LottoGenerateManager.createWinningLotto(winningNumber, bonusNumber);
        return winningLotto;
    }
}
