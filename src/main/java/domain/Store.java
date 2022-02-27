package domain;

import domain.strategy.PurchaseStrategy;
import domain.strategy.RandomPurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private static final int LOTTO_PRICE = 1000;

    public static Lottos purchaseLottos(int inputMoney) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < inputMoney / LOTTO_PRICE; i++) {
            purchase(lottos, new RandomPurchaseStrategy());
        }
        return new Lottos(lottos);
    }

    private static void purchase(List<Lotto> lottos, PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.generateNumbers());
        lottos.add(lotto);
    }

}
