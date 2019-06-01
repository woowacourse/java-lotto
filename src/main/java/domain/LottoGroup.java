package domain;

import java.util.Iterator;
import java.util.List;

public class LottoGroup implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    private LottoGroup(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoGroup from(List<Lotto> lottos) {
        return new LottoGroup(lottos);
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }

    public int totalSize() {
        return lottos.size();
    }

    public Lotto get(int i) {
        return lottos.get(i);
    }
}
