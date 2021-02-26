package lotto.domain.lotto;

import static lotto.utils.Validation.isNumeric;

public class LottoAmount {
    private static final String ERROR_LOTTO_AMOUNT_NOT_NUMERIC = "[ERROR] 로또 구매 수량은 숫자여야 합니다.";
    private static final String ERROR_LOTTO_AMOUNT_NEGATIVE = "[ERROR] 로또 구매 수량은 음수가 될 수 없습니다.";

    private final int value;

    public LottoAmount(int lottoAmount) {
        value = lottoAmount;
    }


    public int getValue() {
        return value;
    }
}
