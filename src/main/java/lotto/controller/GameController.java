package lotto.controller;

import lotto.View.InputView;
import lotto.View.OutputView;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.Lottos;
import lotto.domain.Money;

import java.util.List;

public class GameController {
    private static Money money;
    private static Lottos lottos;

    public GameController() {
        LottoGenerator.generate(1, 45);
    }

    public void run() {
        inputMoney();
        buyLotto();
    }

    private void buyLotto() {
        int quantity = money.lottoQuantity();
        List<Lotto> lottoGroup = LottoGenerator.makeLottos(quantity);
        lottos = new Lottos(lottoGroup);
        printPurchasedLottos(quantity);
    }

    private void printPurchasedLottos(int quantity) {
        OutputView.showLottoQuantity(quantity);
        OutputView.showLottos(lottos.values());
    }

    private void inputMoney() {
        money = new Money(InputView.inputMoney());
    }
}
