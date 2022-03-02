package domain;

import utils.Validator;

public class Count {

    private static final int COUNT_END_NUMBER = 0;
    private static final int COUNT_DECREASE_UNIT = 1;
    public static final String NO_PURCHASE_MANUAL_LOTTO = "0";
    public static final String ERROR_UPPER_THAN_MONEY_MESSAGE = "수동으로 구매하려는 로또 수가 구입금액보다 많습니다.";

    private final int count;

    public Count(final int count) {
        this.count = count;
    }

    public static Count getManualCount(final String input, final Money money) {
        validateInputManualCount(input, money);
        int count = Integer.parseInt(input);
        checkOverPrice(count, money);
        return new Count(count);
    }

    private static void validateInputManualCount(final String input, final Money money) {
        Validator.checkNullOrEmpty(input);
        if (!isPurchaseManualLotto(input)) {
            Validator.checkFormat(input);
        }
    }

    private static boolean isPurchaseManualLotto(String input) {
        return input.equals(NO_PURCHASE_MANUAL_LOTTO);
    }

    private static void checkOverPrice(final int count, final Money money) {
        if (count > money.calculateCounts()) {
            throw new IllegalArgumentException(ERROR_UPPER_THAN_MONEY_MESSAGE);
        }
    }

    public boolean isEnd() {
        return this.count <= COUNT_END_NUMBER;
    }

    public Count decrease() {
        return new Count(this.count - COUNT_DECREASE_UNIT);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Count count1 = (Count) object;

        return count == count1.count;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
