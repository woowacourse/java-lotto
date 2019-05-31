package lotto.domain;

import java.util.List;

public class Lottos {
    List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos create(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public Lotto getIndexByLotto(int index) {
        return lottos.get(index);
    }

    public int getSize() {
        return lottos.size();
    }
}
