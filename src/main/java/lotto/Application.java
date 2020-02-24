package lotto;

import lotto.domain.*;
import lotto.generator.LottoGenerator;
import lotto.generator.LottoNumberRandomGenerator;
import lotto.generator.LottoNumberSelectedGenerator;
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

        Lottos lottos = new Lottos(LottoGenerator.createLottos(paidPrice, new LottoNumberRandomGenerator()));
        OutputView.printLottosNumbers(lottos);
        return lottos;
    }

    private static WinningLotto initWinnerLotto() {
        String winningNumber = InputView.getWinningLottoNumbers();
        String bonusNumber = InputView.getBonusNumber();
        WinningLotto winningLotto =
                LottoGenerator.createWinningLotto(new LottoNumberSelectedGenerator(winningNumber), bonusNumber);
        return winningLotto;
    }
}
