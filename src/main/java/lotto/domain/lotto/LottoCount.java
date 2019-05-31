package lotto.domain.lotto;

import lotto.domain.money.IllegalCountException;
import lotto.domain.money.Money;

public class LottoCount {
    private int count;

    public LottoCount(int count) {
        this.count = count;
    }

    public static LottoCount create(int count, Money money) {
        if (count < 0 || count > money.getLottoCount()) {
            throw new IllegalCountException();
        }
        return new LottoCount(count);
    }

    public int size() {
        return count;
    }
}
