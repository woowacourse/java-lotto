package lotto.model;

import lotto.model.exceptions.IllegalCountException;

public class ManualLottoCount {

    private int count;

    public ManualLottoCount(int count, Money money) {
        if (money.isInputBiggerThanMoney(count) || count < 0) {
            throw new IllegalCountException();
        }
        this.count = count;
    }

    public boolean isCountFinished(int currentCount) {
        return currentCount != this.count;
    }

    public int getCount() {
        return count;
    }
}
