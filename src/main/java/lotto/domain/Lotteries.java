package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class Lotteries implements Iterable<Lotto> {
    private final List<Lotto> lotteries;
    private final Lotto lotto;

    public Lotteries(Lotto lotto) {
        this.lotteries = new ArrayList<>();
        this.lotto = lotto;
    }

    public void addNoFormedLotto(List<Integer> noFormedLotto) {
        this.lotteries.add(lotto.customLotto(noFormedLotto));
    }

    public void addNewLotteries(long count) {
        for (int i = 0; i < count; i++) {
            this.lotteries.add(lotto.createLotto());
        }
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lotteries.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotteries lotteries1 = (Lotteries) o;
        return Objects.equals(lotteries, lotteries1.lotteries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotteries);
    }
}
