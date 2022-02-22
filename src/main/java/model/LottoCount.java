package model;

public class LottoCount {
    final String REGEX = "[0-9]+";

    public LottoCount(String number) {
        validateInsertMoneyBlank(number);
        validateInputMoneyNumber(number);
    }

    private void validateInputMoneyNumber(String number) {
        if (!number.matches(REGEX)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateInsertMoneyBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}

