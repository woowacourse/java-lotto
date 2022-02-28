package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Money;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(Money money) {
        int amount = money.getValue();
        while (amount >= Lotto.PRICE) {
            lottos.add(Lotto.auto());
            amount -= Lotto.PRICE;
        }
    }

    public Lottos(Lotto lotto) {
        lottos.add(lotto);
    }

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
