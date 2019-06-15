package domain;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

    public Lotto get(int idx) {
        return (idx < nonRandomSize()) ? nonRandomLottos.get(idx) : randomLottos.get(idx - nonRandomSize());
    }

    private class LottoGroupIterator implements Iterator<Lotto> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < totalSize();
        }

        @Override
        public Lotto next() {
            Lotto lotto = get(i);

            ++i;
            return lotto;
        }
    }

    @Override
    public String toString() {
        return "LottoGroup{" +
                "nonRandomLottos=" + nonRandomLottos +
                ", randomLottos=" + randomLottos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoGroup that = (LottoGroup) o;
        return Objects.equals(nonRandomLottos, that.nonRandomLottos) &&
                Objects.equals(randomLottos, that.randomLottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nonRandomLottos, randomLottos);
    }
}
