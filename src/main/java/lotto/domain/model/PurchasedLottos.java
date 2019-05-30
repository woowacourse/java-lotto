package lotto.domain.model;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottos {

    private List<Lotto> purchasedLottos = new ArrayList<>();

    public void addLottos(List<Lotto> manualLotto) {
        purchasedLottos.addAll(manualLotto);
    }

    public int size() {
        return purchasedLottos.size();
    }

    public List<Lotto> getLottos() {
        return purchasedLottos;
    }
}
