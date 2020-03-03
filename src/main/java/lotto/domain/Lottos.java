package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

//    public Lottos() {
//        this.lottos = new ArrayList<Lotto>();
//    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Stream<Lotto> stream() {
        return this.lottos.stream();
    }

    public void concat(Lottos lottos) {
        this.lottos.addAll(lottos.getLottos());
    }

    private List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
