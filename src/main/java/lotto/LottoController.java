package lotto;

import java.util.List;

public class LottoController {

    private final InputView inputView;

    LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        int purchaseAmount = inputView.requestPurchaseAmount();
        Cashier cashier = new Cashier();
        List<Lotto> lottos = cashier.payForLotto(purchaseAmount);
    }
}
