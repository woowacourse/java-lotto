package lotto.domain;

import java.util.Set;

public class Lottos {
    private final Set<Lotto> lottos;

    Lottos(Set<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Set<Lotto> getLottos() {
        return lottos;
    }
}
