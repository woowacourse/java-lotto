package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public void add(Lottos lottos) {
        this.lottos.addAll(lottos.lottos());
    }


    public List<Lotto> lottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
