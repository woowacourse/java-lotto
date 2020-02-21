package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(new ArrayList<>(lottos));
    }

    public boolean isSameCount(int count) {
        return lottos.size() == count;
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }

    public LottoCount getCount() {
        return new LottoCount(lottos.size());
    }
}
