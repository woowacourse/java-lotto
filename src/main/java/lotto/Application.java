package lotto;

import lotto.controller.GameController;
import lotto.domain.*;
import lotto.generator.LottoGenerateManager;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

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
        List<List<String>> manualLottoNumbers = InputView.getManualLottoNumbers(manualLottoSize);

        BettingInfo bettingInfo = new BettingInfo(paidPrice, manualLottoSize, manualLottoNumbers);
        LottoGenerateManager lottoGenerateManager = new LottoGenerateManager(bettingInfo);
        Lottos lottos = lottoGenerateManager.createLottos();

        OutputView.printLottoSize(bettingInfo.getManualLottoSize(), bettingInfo.getAutoLottoSize());
        OutputView.printLottosNumbers(lottos);
        return lottos;
    }

    private static WinningLotto initWinnerLotto() {
        List<String> winningNumbers = InputView.getWinningLottoNumbers();
        String bonusNumber = InputView.getBonusNumber();
        return LottoGenerateManager.createWinningLotto(winningNumbers, bonusNumber);
    }
}
