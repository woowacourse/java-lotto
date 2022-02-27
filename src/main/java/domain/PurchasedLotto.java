package domain;

import domain.strategy.PurchaseStrategy;
import domain.strategy.RandomPurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLotto {

    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos = new ArrayList<>();
    private final int inputMoney;

    public PurchasedLotto(int inputMoney) {
        this.inputMoney = inputMoney;
        purchaseMaximumLottos();
    }

    private void purchaseMaximumLottos() {
        for (int i = 0; i < inputMoney / LOTTO_PRICE; i++) {
            purchase(new RandomPurchaseStrategy());
        }
    }

    public void purchase(PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.generateNumbers());
        lottos.add(lotto);
    }

    public PrizeResult prizeResult(WinningNumber winningNumber) {
        return new PrizeResult(inputMoney, lottos, winningNumber);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
