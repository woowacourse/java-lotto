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
        if (isManualAvailable()) {
            this.lottos.add(Lotto.from(inputs));
            purchaseCount.subtractManual();
        }
    }

    public void addAutoLottos() {
        while (purchaseCount.isAutoAvailable()) {
            this.lottos.add(Lotto.ofRandom());
            purchaseCount.subtractAuto();
        }
    }

    public Lottos getLottos() {
        return new Lottos(this.lottos);
    }

    public boolean isManualAvailable() {
        return this.purchaseCount.isManualAvailable();
    }
}
