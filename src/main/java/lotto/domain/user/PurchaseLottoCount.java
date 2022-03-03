package lotto.domain.user;

public class PurchaseLottoCount {

    private static final String ERROR_WRONG_INPUT_COUNT = "[ERROR] 올바른 정수값을 입력해주세요";
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
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_COUNT);
        }
    }

    private void checkDivideCount(final int count, final int maxCount) {
        if (count <= 0 || count > maxCount){
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_COUNT);
        }
    }

    public int getRemainPurchaseLottoCount(final int maxCount) {
        return maxCount-purchaseLottoCount;
    }

    public int getPurchaseLottoCount() {
        return purchaseLottoCount;
    }
}
