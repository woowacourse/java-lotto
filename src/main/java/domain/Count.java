package domain;

import utils.Validator;

public class Count {

    private static final int COUNT_END_NUMBER = 0;
    private static final int COUNT_DECREASE_UNIT = 1;
    private static final String NO_PURCHASE_MANUAL_LOTTO = "0";

    private final int count;

    public Count(final int count) {
        this.count = count;
    }

    public Count decrease() {
        return new Count(this.count - COUNT_DECREASE_UNIT);
    }

    public int getCount() {
        return count;
    }

    public static Count getManualCount(final String input, final Money money) {
        validateInputManualCount(input);
        int count = Integer.parseInt(input);
        money.checkOverPrice(count);
        return new Count(count);
    }

    private static void validateInputManualCount(final String input) {
        Validator.checkNullOrEmpty(input);
        if (!isPurchaseManualLotto(input)) {
            Validator.checkFormat(input);
        }
    }

    private static boolean isPurchaseManualLotto(String input) {
        return input.equals(NO_PURCHASE_MANUAL_LOTTO);
    }

    public boolean isEnd() {
        return this.count <= COUNT_END_NUMBER;
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
