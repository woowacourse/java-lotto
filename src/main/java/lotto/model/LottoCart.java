package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoCart {
    private final List<Lotto> lottos;
    private final PurchaseCount purchaseCount;

    public LottoCart(PurchaseCount purchaseCount) {
        this.lottos = new ArrayList<>();
        this.purchaseCount = purchaseCount;
    }

    public void addManualLotto(List<String> inputs) {
        this.lottos.add(Lotto.from(inputs));
    }

    public void addAutoLottos() {
        while (!purchaseCount.isTotal(lottos.size())) {
            this.lottos.add(Lotto.ofRandom());
        }
    }

    public boolean isManualAvailable() {
        return !purchaseCount.isManual(lottos.size());
    }

    public Lottos getLottos() {
        return new Lottos(this.lottos);
    }

    public PurchaseCount getPurchaseCount() {
        return this.purchaseCount;
    }
}
