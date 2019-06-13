package lotto.model;

import lotto.model.exceptions.IllegalCountException;

public class ManualLottoCount {

    private static final int MINIMUM_LOTTO_COUNT = 0;

    private int count;

    public ManualLottoCount(int count, Money money) {
        if (money.isInputBiggerThanMoney(count) || count < MINIMUM_LOTTO_COUNT) {
            throw new IllegalCountException();
        }
        this.count = count;
    }

    public boolean next(int currentCount) {
        return currentCount != this.count;
    }

    public int getCount() {
        return count;
    }
}
