package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.Number;
import lotto.domain.Rank;
import lotto.domain.Store;
import lotto.domain.WinnerLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money inputMoney = InputView.inputMoney();

        Lottos lottos = new Lottos(buyLottos(inputMoney));
        OutputView.printLottos(lottos.getLottos());

        List<Rank> ranks = lottos.match(createWinnerLotto(winnerNumbers(), bonusNumber()));
        OutputView.printRanks(ranks);

        OutputView.printRate(Rank.calculateReward(ranks).divide(inputMoney));
    }

    private List<Lotto> buyLottos(Money money) {
        Store store = new Store(money);
        return store.buyLottos();
    }

    private WinnerLotto createWinnerLotto(List<Number> winnerNumbers, Number bonusNumber) {
        return new WinnerLotto(new Lotto(winnerNumbers), bonusNumber);
    }

    private List<Number> winnerNumbers() {
        return InputView.inputWinnerNumbers();
    }

    private Number bonusNumber() {
        return InputView.inputBonusNumber();
    }

}
