package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LottosDTO {
    private final List<Lotto> copyLottos;
    private Iterator<Lotto> iterator;

    private LottosDTO(final List<Lotto> lottos) {
        this.copyLottos = new ArrayList<>(lottos);
        iterator = copyLottos.iterator();
    }

    public static LottosDTO of(final List<Lotto> lottos) {
        return new LottosDTO(lottos);
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Lotto next() {
        return iterator.next();
    }
}
