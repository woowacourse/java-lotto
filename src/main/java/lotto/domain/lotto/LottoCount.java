package lotto.domain.lotto;

import lotto.domain.money.Money;
import lotto.domain.money.exception.IllegalCountException;

public class LottoCount {
    private final int count;

    public LottoCount(int count) {
        this.count = count;
    }

    public static LottoCount create(int count, Money money) {
        if (count < 0 || count > money.getLottoCount()) {
            throw new IllegalCountException("수동 로또 개수는 0 이상 입력한 금액으로 살 수 있는 개수 이하여야 합니다.");
        }
        return new LottoCount(count);
    }

    public int size() {
        return count;
    }
}
