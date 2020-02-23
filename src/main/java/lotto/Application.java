package lotto;

import lotto.domain.*;
import lotto.utils.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String args[]) {
        Lottos lottos = initUserLottos();
        WinningLotto winningLotto = initWinnerLotto();

        ResultsDTO resultsDTO = ResultCalculator.getResults(lottos, winningLotto);

        OutputView.printResults(resultsDTO);
    }

    private static Lottos initUserLottos() {
        PaidPrice paidPrice = new PaidPrice(InputView.getPayment());
        OutputView.printLottoCount(paidPrice);

        Lottos lottos = new Lottos(LottoGenerator.createLottos(paidPrice));
        OutputView.printLottiesNumbers(lottos);
        return lottos;
    }

    private static WinningLotto initWinnerLotto() {
        String winningNumberInputByUser = InputView.getWinningLottoNumbers();
        String bonusNumberInputByUser = InputView.getBonusNumber();
        WinningLotto winningLotto =
                LottoGenerator.createWinningLottoByUserInput(winningNumberInputByUser, bonusNumberInputByUser);
        return winningLotto;
    }
}
