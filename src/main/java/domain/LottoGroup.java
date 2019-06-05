package domain;

import java.util.Iterator;
import java.util.List;

public class LottoGroup implements Iterable<Lotto> {
    private final List<Lotto> nonRandomLottos;
    private final List<Lotto> randomLottos;

    private LottoGroup(List<Lotto> nonRandomLottos, List<Lotto> randomLottos) {
        this.nonRandomLottos = nonRandomLottos;
        this.randomLottos = randomLottos;
    }

    public static LottoGroup of(List<Lotto> nonRandomLottos, List<Lotto> randomLottos) {
        return new LottoGroup(nonRandomLottos, randomLottos);
    }

    @Override
    public Iterator<Lotto> iterator() {
        return new LottoGroupIterator();
    }

    public int totalSize() {
        return nonRandomLottos.size() + randomLottos.size();
    }

    public int nonRandomSize() {
        return nonRandomLottos.size();
    }

    public int randomSize() {
        return randomLottos.size();
    }

    public boolean contains(Lotto lotto) {
        return nonRandomLottos.contains(lotto) || randomLottos.contains(lotto);
    }

    private class LottoGroupIterator implements Iterator<Lotto> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < totalSize();
        }

        @Override
        public Lotto next() {
            Lotto lotto = (i < nonRandomLottos.size())
                    ? nonRandomLottos.get(i)
                    : randomLottos.get(i - nonRandomLottos.size());

            ++i;
            return lotto;
        }
    }
}
