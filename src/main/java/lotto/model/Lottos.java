package lotto.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {
    private static final String ERROR_OVER_COUNT;

    static {
        ERROR_OVER_COUNT = "구입한 로또보다 많은 개수입니다.";
    }

    private final List<Lotto> lottoList = new ArrayList<>();

    public Lottos(final int allPurchaseCount, final int manualPurchaseCount) {
        final int autoPurchaseCount = allPurchaseCount - manualPurchaseCount;
        if (autoPurchaseCount < 0) {
            throw new IllegalArgumentException(ERROR_OVER_COUNT);
        }
    }

    public void add(final List<Lotto> lottoList) {
        this.lottoList.addAll(lottoList);
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoList.iterator();
    }

    public int size() {
        return lottoList.size();
    }
}
