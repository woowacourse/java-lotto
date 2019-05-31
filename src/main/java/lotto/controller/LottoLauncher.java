package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.money.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LottoLauncher {

    public static void main(String[] args) {
        Money money = Money.create(InputView.askMoney());
        Lottos manualLottos = getManualLottos(money);
        Lottos automaticLottos = LottoMachine.generateLottos(money.getLottoCount() - manualLottos.size());
        OutputView.printLottos(manualLottos, automaticLottos);

        WinningLotto winningLotto = WinningLotto.create(InputView.askWinningLottoNumbers(),
                InputView.askBonusNumber());

        Lottos totalLottos = manualLottos.append(automaticLottos);
        LottoResult lottoResult = LottoResult.create(money, totalLottos.getPrizes(winningLotto));
        OutputView.printStatistics(lottoResult);
    }

    private static Lottos getManualLottos(Money money) {
        List<Lotto> manualLottos = new ArrayList<>();
        LottoCount lottoCount = LottoCount.create(InputView.askManualLottoCount(), money);

        for (int i = 0; i < lottoCount.size(); i++) {
            manualLottos.add(new Lotto(InputView.askManualLottoNumbers()));
        }
        return LottoMachine.generateLottos(manualLottos);
    }
}
