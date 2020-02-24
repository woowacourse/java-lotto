package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(){ }

    public void addLottos(List<Lotto> targetLottos) {
        Objects.requireNonNull(targetLottos);
        lottos.addAll(targetLottos);
    }

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}