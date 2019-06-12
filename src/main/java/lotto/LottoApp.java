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

        List<Lotto> manualLottos = InputView.generateManualLottoCreator(manualLottoQuantity);
        List<Lotto> autoLottos = LottoFactory.createAutoLottos(autoLottoQuantity);

        OutputView.printPurchaseResult(manualLottoQuantity, autoLottoQuantity);

        Lottos myLottos = new Lottos(combineLottos(autoLottos, manualLottos));
        WinningLotto winningLotto = InputView.inputWinningLotto();

        LottosResult result = new LottosResult(winningLotto, myLottos);

        OutputView.printLottosResult(result);
        OutputView.printROI(result);
    }

    private static List<Lotto> combineLottos(List<Lotto> autoLottos, List<Lotto> manualLottos) {
        List<Lotto> totalLottos = new ArrayList<>();

        totalLottos.addAll(manualLottos);
        totalLottos.addAll(autoLottos);
        return totalLottos;
    }
}
