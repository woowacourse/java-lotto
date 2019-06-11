package lotto;

import lotto.domain.*;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoApp {
    public static void main(String[] args) {
        Money money = InputView.inputMoney();
        int manualLottoQuantity = InputView.inputManualLotto(money);
        int autoLottoQuantity = money.getBuyableLottoQuantity() - manualLottoQuantity;

        List<Lotto> totalLottos = new ArrayList<>();
        List<Lotto> manualLottos = InputView.generateManualLottoCreator(manualLottoQuantity);
        List<Lotto> autoLottos = LottoFactory.createAutoLottos(autoLottoQuantity);

        OutputView.printPurchaseResult(manualLottoQuantity, autoLottoQuantity);

        totalLottos.addAll(manualLottos);
        totalLottos.addAll(autoLottos);

        Lottos myLottos = new Lottos(totalLottos);
        WinningLotto winningLotto = InputView.inputWinningLotto();

        LottosResult result = new LottosResult(winningLotto, myLottos);

        OutputView.printLottosResult(result);
        OutputView.printROI(result);
    }
}
