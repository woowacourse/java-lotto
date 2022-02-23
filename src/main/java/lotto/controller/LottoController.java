package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        buyLotto();
    }

    private void buyLotto() {
        int lottoCount = getLottoCount();
        Lottos lottos = new Lottos(lottoCount);

        Output.lottoCount(lottoCount);
        Output.lottos(lottos);
    }

    private int getLottoCount() {
        Output.askPurchaseAmount();

        PurchaseAmount purchaseAmount = new PurchaseAmount(Input.purchaseAmount());
        return lottoMachine.getLottoCount(purchaseAmount);
    }
}
