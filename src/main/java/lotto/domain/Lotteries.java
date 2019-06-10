package lotto.domain;

import lotto.domain.autocreatelotto.AutoCreateLotto;
import lotto.domain.customcreatelotto.CustomCreateLotto;

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

    public Lotteries() {
        this.lotteries = new ArrayList<>();
    }

    public void addCustomLotto(List<Integer> noFormedLotto, CustomCreateLotto customCreateLotto) {
        this.lotteries.add(Lotto.customLotto(noFormedLotto, customCreateLotto));
    }

    public void addAutoLotteries(long count, AutoCreateLotto autoCreateLotto) {
        for (int i = 0; i < count; i++) {
            this.lotteries.add(Lotto.createLotto(autoCreateLotto));
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
