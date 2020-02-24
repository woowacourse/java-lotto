package lotto.domain;

import java.util.List;

public class Lottos {
    public static int PRICE_PER_LOTTO = 1000;

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }
}
