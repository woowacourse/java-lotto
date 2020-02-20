package lotto;

import lotto.domain.Lottos;
import lotto.domain.PaidPrice;
import lotto.domain.WinningLotto;
import lotto.utils.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String args[]) {
        PaidPrice paidPrice = new PaidPrice(InputView.getPayment());
        OutputView.printLottoCount(paidPrice);

        Lottos lottos = new Lottos(LottoGenerator.createLottos(paidPrice));
        OutputView.printLottiesNumbers(lottos);

        String winningNumberInputByUser = InputView.getWinningLottoNumbers();
        String bonusNumberInputByUser = InputView.getBonusNumber();
        WinningLotto winningLotto =
                LottoGenerator.createWinningLottoByUserInput(winningNumberInputByUser, bonusNumberInputByUser);
    }
}
