package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final int count) {
        List<Lotto> newLottos = IntStream.range(0, count)
                .mapToObj(i -> new Lotto())
                .collect(Collectors.toList());
        lottos = new ArrayList<>(newLottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}