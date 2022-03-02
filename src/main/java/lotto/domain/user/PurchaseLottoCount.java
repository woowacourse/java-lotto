package lotto.domain.user;

import lotto.exception.InvalidException;

public class PurchaseLottoCount {

    private int purchaseLottoCount;

    public PurchaseLottoCount(final String count, final int maxCount) {
        checkInputCount(count, maxCount);
        this.purchaseLottoCount = Integer.parseInt(count);
    }

    private void checkInputCount(final String count, final int maxCount) {
        checkValidateInt(count);
        checkDivideCount(Integer.parseInt(count), maxCount);
    }

    private void checkValidateInt(final String count) {
        try {
            Integer.parseInt(count);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_COUNT);
        }
    }

    private void checkDivideCount(final int count, final int maxCount) {
        if (count <= 0 || count > maxCount){
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_COUNT);
        }
    }

    public int getRemainPurchaseLottoCount(final int maxCount) {
        return maxCount-purchaseLottoCount;
    }

    public int getPurchaseLottoCount() {
        return purchaseLottoCount;
    }
}
