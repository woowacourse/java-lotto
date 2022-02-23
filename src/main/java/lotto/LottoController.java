package lotto;

import java.util.List;

public class LottoController {

    public void run() {
        int money = InputView.inputMoney();
        Store store = new Store(money, new LottoGenerator());
        Lottos lottos = new Lottos(store.buyLottos());

        List<Number> winnerNumbers = InputView.inputNumbers();
        Number bonusNumber = InputView.inputNumber();

        WinnerLotto winnerLotto = new WinnerLotto(new Lotto(winnerNumbers), bonusNumber);
        List<Rank> ranks = lottos.matchRanks(winnerLotto);
        OutputView.printRanks(ranks);

        Money reward = Rank.calculateReward(ranks);
        Rate rate = reward.divide(new Money(money));
        OutputView.printRate(rate);
    }
}
