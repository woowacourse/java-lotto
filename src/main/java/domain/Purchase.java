package domain;

import static utils.Messages.*;

public class Purchase {

    private static final int MONEY_UNIT = 1000;

    private final int money;
    private final int autoCount;
    private final int manualCount;

    public Purchase(int money, int manualCount) {
        validate(money, manualCount);
        this.money = money;
        this.manualCount = manualCount;
        this.autoCount = money / 1000 - manualCount;
    }

    private void validate(int money, int manualCount) {
        isOverThousand(money);
        isDivideByThousand(money);
        isRightLottoCount(money, manualCount);
    }

    private void isRightLottoCount(int money, int manualCount) {
        if (manualCount > money / 1000) {
            throw new IllegalArgumentException(COUNT_OVER_MONEY_ERROR_MESSAGE);
        }
    }

    private void isOverThousand(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException(MONEY_OVER_THOUSANDS_ERROR_MESSAGE);
        }
    }

    private void isDivideByThousand(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(MONEY_DIVIDE_ERROR_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }
}