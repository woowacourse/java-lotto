package lotto;

import java.util.List;

public class LottoController {

    public void run() {
        int money = InputView.inputMoney();

        Lottos lottos = new Lottos(buyLottos(money));
        OutputView.printAmountOfLottos(lottos.amount());

        List<Rank> ranks = lottos.matchRanks(createWinnerLotto(winnerNumbers(), bonusNumber()));
        OutputView.printRanks(ranks);

        Rate rate = calculateRate(money, ranks);
        OutputView.printRate(rate);
    }

    private List<Lotto> buyLottos(int money) {
        Store store = new Store(money, new LottoGenerator());
        return store.buyLottos();
    }

    private WinnerLotto createWinnerLotto(List<Number> winnerNumbers, Number bonusNumber) {
        return new WinnerLotto(new Lotto(winnerNumbers), bonusNumber);
    }

    private List<Number> winnerNumbers() {
        return InputView.inputNumbers();
    }

    private Number bonusNumber() {
        return InputView.inputNumber();
    }

    private Rate calculateRate(int money, List<Rank> ranks) {
        Money reward = Rank.calculateReward(ranks);
        return reward.divide(new Money(money));
    }

}
