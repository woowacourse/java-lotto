package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    void add(final Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
