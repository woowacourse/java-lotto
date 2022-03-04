package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generateManual(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos generateAuto(int count) {
        return new Lottos(IntStream.range(0, count)
                .mapToObj(i -> Lotto.generateAuto())
                .collect(Collectors.toList()));
    }

    public static Lottos getTotalLottos(Lottos auto, Lottos manual) {
        manual.lottos.addAll(auto.lottos);
        return manual;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
