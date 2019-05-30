package lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.view.InputView;
import lotto.view.OutputView;

public class CUILottoApp {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        List<String[]> manuals = createManuals(money);
        List<Lotto> lottos = LottoFactory.createLottos(manuals, money);

        OutputView.printLottos(manuals.size(), lottos);

        WinningLotto winningLotto = createWinningLotto();
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        OutputView.printLottoResult(lottoResult);
        OutputView.printLottoYield(lottoResult, money);
    }

    private static WinningLotto createWinningLotto() {
        Lotto lotto = new ManualLottoCreator(InputView.inputUserString()).create();
        Number number = InputView.createBonusNumber();
        return new WinningLotto(lotto, number);
    }

    private static List<String[]> createManuals(Money money) {
        int manualSize = InputView.inputManualSize();
        money.checkManualSize(manualSize);
        return InputView.createManualLottos(manualSize);
    }

}
