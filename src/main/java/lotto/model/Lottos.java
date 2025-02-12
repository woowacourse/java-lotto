package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    public static final int UNIT_PRICE = 1_000;
    private final List<Lotto> lottos = new ArrayList<>();

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return lottos.stream()
                .toList();
    }
}
