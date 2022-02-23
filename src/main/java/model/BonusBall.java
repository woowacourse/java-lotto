package model;

public class BonusBall {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    public BonusBall(String number) {
        validateInputNumberBlank(number);
        validateInputBonusNumber(number);
        validateInputBonusNumberOutOfRange(number);
    }

    private void validateInputNumberBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateInputBonusNumber(String number) {
        if (!number.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateInputBonusNumberOutOfRange(String number) {
        if (Integer.parseInt(number) < LOTTO_MIN_NUMBER || Integer.parseInt(number) > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException();
        }
    }
}
