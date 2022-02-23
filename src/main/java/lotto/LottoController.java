package lotto;

import java.util.List;

public class LottoController {

    public void run() {
        int money = InputView.inputMoney();
        Store store = new Store(money, new LottoGenerator());
        Lottos lottos = new Lottos(store.buyLottos());

        List<Number> winnerNumbers = InputView.inputNumbers();
    }
}
