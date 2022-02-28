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
        Money totalMoney = Money.createMoney(InputView.inputMoney());
        int manualLottoCount = InputView.inputManualLottoCount();
        Money manualMoney = Money.createMoney(manualLottoCount * Store.LOTTO_PRICE);
        validateManualLottoCountOutOfMoney(totalMoney, manualMoney);

        List<Lotto> manualLottos = InputView.inputManualLottos(manualLottoCount);

        List<Lotto> automaticLottos = buyLottos(totalMoney.minus(manualMoney));
        manualLottos.addAll(automaticLottos);
        Lottos lottos = new Lottos(manualLottos);
        OutputView.printLottoCount(manualLottoCount, lottos.getLottos().size() - manualLottoCount);
        OutputView.printLottos(lottos.getLottos());

        List<Rank> ranks = lottos.matchRanks(createWinnerLotto(winnerNumbers(), bonusNumber()));
        OutputView.printRanks(ranks);

        Rate rate = calculateRate(totalMoney, ranks);
        OutputView.printRate(rate);
    }

    private void validateManualLottoCountOutOfMoney(Money totalMoney, Money anotherMoney) {
        if (anotherMoney.isGreaterThan(totalMoney)) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수가 구매 가능한 로또 수보다 크다.");
        }
    }

    private List<Lotto> buyLottos(Money money) {
        Store store = new Store(money);
        return store.buyLottos();
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
