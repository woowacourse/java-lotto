package lotto;

import java.util.List;

import lotto.creator.LottosFactory;
import lotto.creator.ManualLottoCreator;
import lotto.domain.*;
import lotto.domain.Number;
import lotto.view.InputView;
import lotto.view.OutputView;

public class CUILottoApp {
    public static void main(String[] args) {
        try {
            Money money = new Money(InputView.inputMoney());
            List<String> manuals = createManuals(money);
            List<Lotto> lottos = LottosFactory.create(manuals, money);

            OutputView.printLottos(manuals.size(), lottos);

            WinningLotto winningLotto = createWinningLotto();
            LottoResult lottoResult = new LottoResult(lottos, winningLotto);

            OutputView.printLottoResult(lottoResult);
            OutputView.printLottoYield(lottoResult, money);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static WinningLotto createWinningLotto() {
        ManualLottoCreator manualLottoCreator = new ManualLottoCreator(null);
        Lotto lotto = manualLottoCreator.createLotto(InputView.inputLottoString());
        Number number = InputView.createBonusNumber();
        return new WinningLotto(lotto, number);
    }

    private static List<String> createManuals(Money money) {
        int manualSize = InputView.inputManualSize();
        money.checkManualSize(manualSize);
        return InputView.createManualLottos(manualSize);
    }

}
