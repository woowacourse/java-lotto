package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        this(new ArrayList<>());
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Lottos makeLottos(int payCount) {
        for (int i = 0; i < payCount; i++) {
            Lotto lotto = Lotto.fromGenerator(new RandomNumberGenerator());
            lottos.add(lotto);
        }

        return new Lottos(this.lottos);
    }

    public List<List<Integer>> toInts() {
        return lottos.stream()
                     .map(Lotto::toInts)
                     .collect(Collectors.toList());
    }
}
