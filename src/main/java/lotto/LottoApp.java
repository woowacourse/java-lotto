package lotto;

import lotto.domain.*;
import lotto.domain.creator.AutoLottoCreator;
import lotto.domain.creator.ManualLottoCreator;
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

        ManualLottoCreator manualCreator
                = InputView.generateManualLottoCreator(manualLottoQuantity);
        AutoLottoCreator autoCreator = new AutoLottoCreator();

        List<Lotto> totalLottos = new ArrayList<>();
        List<Lotto> manualLottos = LottoFactory.createLottoList(manualLottoQuantity, manualCreator);
        List<Lotto> autoLottos = LottoFactory.createLottoList(autoLottoQuantity, autoCreator);

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
