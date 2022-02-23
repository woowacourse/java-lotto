package model;

public class LottoCount {
    private static final int ZERO = 0;
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int UNIT = 1000;
    private static final String LOTTO_COUNT_BLANK_ERROR_MESSAGE = "[Error]: 금액을 입력해주세요.";
    private static final String LOTTO_COUNT_NUMBER_ERROR_MESSAGE = "[Error]: 금액은 숫자를 입력해주세요.";
    private static final String LOTTO_COUNT_UNIT_ERROR_MESSAGE = "[Error]: 금액은 천원 단위여야 합니다.";

    private int count;

    public LottoCount(String money) {
        validateInsertMoneyBlank(money);
        validateInputMoneyNumber(money);
        this.count = makeMoneyToNumber(money);
    }

    private void validateInsertMoneyBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException(LOTTO_COUNT_BLANK_ERROR_MESSAGE);
        }
    }

    private void validateInputMoneyNumber(String number) {
        if (!number.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException(LOTTO_COUNT_NUMBER_ERROR_MESSAGE);
        }
    }

    private int makeMoneyToNumber(String money) {
        validateThousandUnitInputMoney(money);
        return Integer.parseInt(money) / UNIT;
    }

    private void validateThousandUnitInputMoney(String money) {
        if (Integer.parseInt(money) % UNIT != 0) {
            throw new IllegalArgumentException(LOTTO_COUNT_UNIT_ERROR_MESSAGE);
        }
    }

    public boolean isZero() {
        return count == ZERO;
    }

    public void makeLotto() {
        count--;
    }

    public int getCount() {
        return count;
    }
}

