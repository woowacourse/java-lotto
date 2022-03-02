package lotto.domain;

public class ManualLottoAmount {

    private static final String ERROR_WRONG_INPUT_NUMBER = "[ERROR] 수동으로 구매할 로또 수는 구입금액 내에서 구매가능한 0 이상의 수여야 합니다.";

    private final int amount;

    public ManualLottoAmount(String number, int maxAmount) {
        checkValidInt(number);
        checkValidAmount(Integer.parseInt(number), maxAmount);
        this.amount = Integer.parseInt(number);
    }

    private void checkValidInt(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_NUMBER);
        }
    }

    private void checkValidAmount(int number, int maxAmount) {
        if (number > maxAmount || number < 0) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_NUMBER);
        }
    }

    public int getAmount() {
        return amount;
    }
}
