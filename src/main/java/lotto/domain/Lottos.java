package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> values) {
        lottos.addAll(values);
    }

    public List<Lotto> values() {
        return this.lottos;
    }
}
