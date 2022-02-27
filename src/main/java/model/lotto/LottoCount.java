package model.lotto;

import java.util.Objects;

import model.exception.LottoCountException;
import utils.InputValidateUtils;

public class LottoCount {
    private static final int ZERO = 0;
    private static final int UNIT = 1000;

    private int count;

    public LottoCount(String money) {
        InputValidateUtils.inputBlank(money, LottoCountException.BLANK_ERROR.getMassage());
        InputValidateUtils.inputNumber(money, LottoCountException.NUMBER_ERROR.getMassage());
        validateInputZero(money);
        this.count = makeMoneyToNumber(money);
    }

    private int makeMoneyToNumber(String money) {
        validateThousandUnitInputMoney(money);
        return Integer.parseInt(money) / UNIT;
    }

    private void validateThousandUnitInputMoney(String money) {
        if (Integer.parseInt(money) % UNIT != 0) {
            throw new IllegalArgumentException(LottoCountException.UNIT_ERROR.getMassage());
        }
    }

    private void validateInputZero(String money) {
        if (Integer.parseInt(money) == 0) {
            throw new IllegalArgumentException(LottoCountException.LOWER_THAN_THOUSAND.getMassage());
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
