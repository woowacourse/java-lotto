package lotto.domain;

public class ManualLottoCount {

    private static final String ERROR_WRONG_INPUT_NUMBER = "[ERROR] 수동으로 구매할 로또 수는 구입금액 내에서 구매가능한 0 이상의 수여야 합니다.";

    private final int count;

    public ManualLottoCount(String count, int maxCount) {
        checkValidInt(count);
        checkValidCount(Integer.parseInt(count), maxCount);
        this.count = Integer.parseInt(count);
    }

    private void checkValidInt(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_NUMBER);
        }
    }

    private void checkValidCount(int number, int maxAmount) {
        if (number > maxAmount || number < 0) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_NUMBER);
        }
    }

    public int getCount() {
        return count;
    }
}
