package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(Money money) {
        this.lottos = new ArrayList<>();
        purchaseLotto(money);
    }

    private void purchaseLotto(Money money) {
        for (int i = 0; i < getLottoCount(money); i++) {
            lottos.add(new Lotto(new PickedNumbers()));
        }
    }

    private int getLottoCount(Money money) {
        return money.getMoney() / 1000;
    }

    public int getLottosSize() {
        return lottos.size();
    }
}
