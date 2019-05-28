package lotto.domain;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto implements Iterable<LottoNumber> {
    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    public int size() {
        return lotto.size();
    }

    public String getLotto() {
        return lotto.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.joining(", "));
    }

    @Override
    public Iterator<LottoNumber> iterator() {
        return lotto.iterator();
    }
}
