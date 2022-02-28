package lotto.model.lotto;

import java.util.Objects;

import lotto.model.message.LottoCountExceptionMessage;
import lotto.utils.ConverterUtils;
import lotto.utils.InputValidateUtils;

public class LottoCount {
    private static final int NON_REMAINDER = 0;
    private static final int END = 0;
    private static final int NOTHING = 0;
    private static final int UNIT = 1000;

    private int count;

    public LottoCount(String money) {
        InputValidateUtils.inputBlank(money, LottoCountExceptionMessage.BLANK_ERROR.getMassage());
        InputValidateUtils.inputNumber(money, LottoCountExceptionMessage.NUMBER_ERROR.getMassage());
        validateInputZero(money);
        this.count = makeLottoCount(money);
    }

    private int makeLottoCount(String money) {
        validateThousandUnitInputMoney(money);
        return ConverterUtils.convertStringToInt(money) / UNIT;
    }

    private void validateThousandUnitInputMoney(String money) {
        if (ConverterUtils.convertStringToInt(money) % UNIT != NON_REMAINDER) {
            throw new IllegalArgumentException(LottoCountExceptionMessage.UNIT_ERROR.getMassage());
        }
    }

    private void validateInputZero(String money) {
        if (ConverterUtils.convertStringToInt(money) == NOTHING) {
            throw new IllegalArgumentException(LottoCountExceptionMessage.LOWER_THAN_THOUSAND.getMassage());
        }
    }

    public boolean isZero() {
        return count == END;
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
