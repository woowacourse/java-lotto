package model;

public class LottoCount {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int UNIT = 1000;
    private int money;

    public LottoCount(String number) {
        validateInsertMoneyBlank(number);
        validateInputMoneyNumber(number);
        this.money = makeMoneyToNumber(number);
    }

    private int makeMoneyToNumber(String number) {
        validateThousandUnitInputMoney(number);
        return Integer.parseInt(number);
    }

    private void validateThousandUnitInputMoney(String number) {
        if (Integer.parseInt(number) % UNIT != 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateInputMoneyNumber(String number) {
        if (!number.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateInsertMoneyBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public int getMoney() {
        return money;
    }
}

