package lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class Lottos {
    private final Set<Lotto> lottos;

    public Lottos(Set<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Set<Lotto> getLottos() {
        return lottos;
    }

    public Lottos add(Lottos others) {
        Set<Lotto> result = new HashSet<>(this.lottos);
        result.addAll(others.lottos);
        return new Lottos(result);
    }

    @Override
    public String toString() {
        return "Lottos{" +
            "lottos=" + lottos +
            '}';
    }
}
