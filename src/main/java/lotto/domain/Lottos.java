package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos, final int count) {
        List<Lotto> newLottos = new ArrayList<>(lottos);
        for (int i = 0; i < count; i++) {
            newLottos.add(new Lotto());
        }
        this.lottos = newLottos;
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}