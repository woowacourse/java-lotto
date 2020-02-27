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

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public void addAll(List<Lotto> lottoBundle) {
        this.lottos.addAll(lottoBundle);
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }
}
