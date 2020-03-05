package lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class Lottos {
    private static final int LOTTO_PRICE = 1_000;
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

    Money toPrice() {
        return new Money(lottos.size() * LOTTO_PRICE);
    }

    @Override
    public String toString() {
        return "Lottos{" +
            "lottos=" + lottos +
            '}';
    }
}
