package lotto.controller;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.Store;
import lotto.domain.WinnerLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money totalMoney = Money.createLottoMoney(InputView.inputMoney());
        int manualLottoCount = InputView.inputManualLottoCount();

        Lottos lottos = buyLottos(totalMoney, manualLottoCount);
        OutputView.printLottos(manualLottoCount, lottos.getLottos());

        List<Rank> ranks = lottos.matchRanks(createWinnerLotto(winnerNumbers(), bonusNumber()));
        OutputView.printRanks(ranks);
        OutputView.printRate(calculateRate(totalMoney, ranks));
    }

    private Lottos buyLottos(Money totalMoney, int manualLottoCount) {
        InputView.printManualLottos(manualLottoCount);
        return new Lottos(new Store(totalMoney).buyLottos(manualLottoCount));
    }

    private WinnerLotto createWinnerLotto(Lotto winnerNumbers, LottoNumber bonusNumber) {
        return new WinnerLotto(winnerNumbers, bonusNumber);
    }

    private Lotto winnerNumbers() {
        return new Lotto(InputView.inputWinnerNumbers());
    }

    private LottoNumber bonusNumber() {
        return InputView.inputBonusNumber();
    }

    private Rate calculateRate(Money money, List<Rank> ranks) {
        Money reward = Rank.calculateReward(ranks);
        return new Rate(reward.divide(money));
    }

}
