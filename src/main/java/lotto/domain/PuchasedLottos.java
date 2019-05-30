package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PuchasedLottos {

    private List<Lotto> purchasedLottos = new ArrayList<>();

    public void addPurchasedLotto(Lotto purchasedLotto) {
        this.purchasedLottos.add(purchasedLotto);
    }
}
