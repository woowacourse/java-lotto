package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generate(Money money) {
        for (int i = 0; i < money.purchaseCount(); i++) {
            lottos.add(Lotto.of(LottoMaker.generator()));
        }
        return of(lottos);
    }

    private static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
