package lotto;

import lotto.domain.*;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApp {
    public static void main(String[] args) {
        Money money = InputView.inputMoney();
        int manualLottoQuantity = InputView.inputManualLotto(money);
        int autoLottoQuantity = money.getBuyableLottoQuantity() - manualLottoQuantity;

        Lottos myLottos = InputView.generateLottos(manualLottoQuantity, autoLottoQuantity);

        OutputView.printPurchaseResult(manualLottoQuantity, autoLottoQuantity);
        OutputView.printPurchaseLottos(myLottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();

        LottosResult result = new LottosResult(winningLotto, myLottos);

        OutputView.printLottosResult(result);
        OutputView.printROI(result);
    }
}
