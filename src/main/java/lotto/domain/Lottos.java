package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(int count) {
        this(createRandomLottos(count));
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    private static List<Lotto> createRandomLottos(int count) {
        List<Lotto> randomLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            randomLottos.add(new Lotto());
        }
        return randomLottos;
    }
}
