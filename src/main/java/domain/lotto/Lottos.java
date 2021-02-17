package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        ArrayList<Lotto> copy = new ArrayList<>(this.lottos);
        return Collections.unmodifiableList(copy);
    }
}
