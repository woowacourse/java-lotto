package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.money.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoLauncher {

    public static void main(String[] args) {
        Money money = Money.create(InputView.askMoney());
        LottoCount manualLottoCount = LottoCount.create(InputView.askManualLottoCount(), money);
        List<List<Integer>> manualLottos = InputView.askManualLottos(manualLottoCount.size());

        Lottos lottos = LottoMachine.generateLottos(manualLottos, money);

        OutputView.printLottos(lottos, manualLottoCount.size());
        WinningLotto winningLotto = WinningLotto.create(InputView.askWinningLottoNumbers(),
                InputView.askBonusNumber());
        LottoResult lottoResult = LottoResult.create(money, lottos.getPrizes(winningLotto));
        OutputView.printStatistics(lottoResult);
    }
}
