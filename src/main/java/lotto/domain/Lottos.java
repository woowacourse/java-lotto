package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final List<Lotto> lottos = new ArrayList<>();

    public Lottos(Money money) {
        generate(money);
    }

    private void generate(Money money) {
        for (int i = 0; i < money.purchaseCount(); i++) {
            lottos.add(new Lotto(LottoMaker.generator()));
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
