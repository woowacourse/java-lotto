package lotto.domain.model;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLotto {

    private List<Lotto> purchasedLotto = new ArrayList<>();

    public void addLottos(List<Lotto> lotto) {
        purchasedLotto.addAll(lotto);
    }

    public void addLotto(Lotto lotto) {
        this.purchasedLotto.add(lotto);
    }

    public int size() {
        return purchasedLotto.size();
    }

    public List<Lotto> getLotto() {
        return purchasedLotto;
    }
}
