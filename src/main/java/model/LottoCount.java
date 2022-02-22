package model;

public class LottoCount {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int UNIT = 1000;
    private int count;

    public LottoCount(String money) {
        validateInsertMoneyBlank(money);
        validateInputMoneyNumber(money);
        this.count = makeMoneyToNumber(money);
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

    private int makeMoneyToNumber(String money) {
        validateThousandUnitInputMoney(money);
        return Integer.parseInt(money) / UNIT;
    }

    private void validateThousandUnitInputMoney(String money) {
        if (Integer.parseInt(money) % UNIT != 0) {
            throw new IllegalArgumentException();
        }
    }

    public int getCount() {
        return count;
    }
}

