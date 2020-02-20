package lotto;

import lotto.domain.*;
import lotto.utils.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    private static PaidPrice paidPrice;
    private static Lottos lottos;
    private static WinningLotto winningLotto;

    public static void main(String args[]) {
        initUserLottos();
        initWinnerLotto();

        ResultsDTO resultsDTO = ResultCalculator.getResults(lottos, winningLotto);

        System.out.println();
        OutputView.printResults(resultsDTO);
    }

    private static void initUserLottos() {
        paidPrice = new PaidPrice(InputView.getPayment());
        OutputView.printLottoCount(paidPrice);

        lottos = new Lottos(LottoGenerator.createLottos(paidPrice));
        OutputView.printLottiesNumbers(lottos);
    }

    private static void initWinnerLotto() {
        String winningNumberInputByUser = InputView.getWinningLottoNumbers();
        String bonusNumberInputByUser = InputView.getBonusNumber();
        winningLotto =
                LottoGenerator.createWinningLottoByUserInput(winningNumberInputByUser, bonusNumberInputByUser);

    }
}
