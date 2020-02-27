package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    public static int PRICE_PER_LOTTO = 1000;

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList(lottos);
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public int getLottosSize() { return this.lottos.size(); }

    public Lottos addLottos(Lottos lottosToAdd) {
        this.lottos.addAll(lottosToAdd.getLottos());
        return new Lottos(lottos);
    }
}
