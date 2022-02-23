package model;

public class BonusBall {
    private static final String REGEX_NUMBER = "[0-9]+";

    public BonusBall(String number) {
        validateInputNumberBlank(number);
        validateInputBonusNumber(number);
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
}
