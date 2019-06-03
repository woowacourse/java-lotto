package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LottosDto {
    private final List<Lotto> copyLottos;

    private Iterator<Lotto> iterator;

    private LottosDto(final List<Lotto> lottos) {
        this.copyLottos = new ArrayList<>(lottos);
        iterator = copyLottos.iterator();
    }

    public static LottosDto of(final List<Lotto> lottos) {
        return new LottosDto(lottos);
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Lotto next() {
        return iterator.next();
    }
}
