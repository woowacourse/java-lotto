package model;

public class LottoCount {

    public LottoCount(String number) {
        validateInsertMoneyBlank(number);
    }

    private void validateInsertMoneyBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
