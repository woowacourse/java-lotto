package model.lotto;

import utils.InputValidateUtils;

import java.util.Objects;

public class LottoCount {
    private static final int ZERO = 0;
    private static final int UNIT = 1000;
    private static final String LOTTO_COUNT_BLANK_ERROR_MESSAGE = "[Error]: 금액을 입력해주세요.";
    private static final String LOTTO_COUNT_NUMBER_ERROR_MESSAGE = "[Error]: 금액은 숫자를 입력해주세요.";
    private static final String LOTTO_COUNT_UNIT_ERROR_MESSAGE = "[Error]: 금액은 천원 단위여야 합니다.";
    private static final String LOTTO_COUNT_LOWER_THAN_THOUSAND = "[Error]: 금액은 1000원 이상이어야 합니다.";

    private int count;

    public LottoCount(String money) {
        InputValidateUtils.inputBlank(money, LOTTO_COUNT_BLANK_ERROR_MESSAGE);
        InputValidateUtils.inputNumber(money, LOTTO_COUNT_NUMBER_ERROR_MESSAGE);
        validateInputZero(money);
        this.count = makeMoneyToNumber(money);
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

    private void validateInputZero(String money) {
        if (Integer.parseInt(money) == 0) {
            throw new IllegalArgumentException(LOTTO_COUNT_LOWER_THAN_THOUSAND);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
