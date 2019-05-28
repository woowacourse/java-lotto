package lotto.domain;

import java.util.Iterator;
import java.util.List;

public class Lotto implements Iterable<LottoNumber> {
    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    public int size() {
        return lotto.size();
    }

    @Override
    public Iterator<LottoNumber> iterator() {
        return lotto.iterator();
    }
}
