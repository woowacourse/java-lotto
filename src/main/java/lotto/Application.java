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

        Results results = new Results(lottos, winningLotto);
        results.calculateResults();

        System.out.println();
        OutputView.printResults(results);
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
