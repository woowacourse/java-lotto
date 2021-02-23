package lotto.domain;

import lotto.exception.IllegalManualLottoAmountException;

import java.util.regex.Pattern;

public class ManualLottoAmount {
    private static final Pattern NUMBER_FORMAT = Pattern.compile("^[0-9]*$");

    private final int value;

    public ManualLottoAmount(String input, int purchasableLottoCount) {  // TODO : Money 받을지 그대로 int 받을지
        validateManualLottoAmount(input, purchasableLottoCount);
        this.value = Integer.parseInt(input);
    }

    private void validateManualLottoAmount(String amount, int purchasableLottoCount) {
        validateInputByFormat(amount);
        validateInputByMoney(amount, purchasableLottoCount);
    }

    private void validateInputByFormat(String amount) {
        if (!NUMBER_FORMAT.matcher(amount).matches()) {
            throw new IllegalManualLottoAmountException(amount + " : 올바른 형식이 아닙니다.");
        }
    }

    private void validateInputByMoney(String amount, int purchasableLottoCount) {
        if (Integer.parseInt(amount) > purchasableLottoCount) {
            throw new IllegalManualLottoAmountException(amount + " : 소지금으로 구매할 수 있는 범위를 초과합니다.");
        }
    }

    public int getValue() {
        return this.value;
    }
}
