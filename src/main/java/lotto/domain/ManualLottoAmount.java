package lotto.domain;

import lotto.exception.IllegalManualLottoAmountException;

import java.util.regex.Pattern;

public class ManualLottoAmount {
    private final int value;

    public ManualLottoAmount(String amount, int autoLottoCount) {
        validateNumberByFormat(amount);
        this.value = Integer.parseInt(amount);
    }

    private void validateNumberByFormat(String amount) {
        if (!Pattern.compile("^[0-9]*$").matcher(amount).matches()) {
            throw new IllegalManualLottoAmountException(amount + " : 올바른 형식이 아닙니다.");
        }
    }

    public int getValue() {
        return this.value;
    }
}
