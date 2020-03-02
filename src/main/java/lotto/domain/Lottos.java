package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<Lotto>();
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void concat(Lottos lottos) {
        this.lottos.addAll(lottos.getLottos());
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
