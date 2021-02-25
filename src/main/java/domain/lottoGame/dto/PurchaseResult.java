package domain.lottoGame.dto;

import domain.lottoGame.Lottos;

public class PurchaseResult {

    private final Lottos purchasedLottos;
    private final int numberOfRandomPurcased;
    private final int numberOfManualPurchase;

    public PurchaseResult(Lottos purchasedLottos, int numberOfManualPurchase, int numberOfRandomPurchase) {
        this.purchasedLottos = purchasedLottos;
        this.numberOfRandomPurcased = numberOfRandomPurchase;
        this.numberOfManualPurchase = numberOfManualPurchase;
    }

    public int getNumberOfRandomPurchase() {
        return numberOfRandomPurcased;
    }

    public int getNumberOfManualPurchase() {
        return numberOfManualPurchase;
    }

    public Lottos getPurchasedLottos() {
        return purchasedLottos;
    }
}
