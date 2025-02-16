package lotto.util;

import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_SIZE;

public enum ErrorHandler {

    INVALID_NUMBER("로또 구입 금액은 숫자여야 합니다."),
    INVALID_AMOUNT(String.format("로또 구입 금액이 %d원 이상이어야 합니다.", LOTTO_MONEY_UNIT)),
    INVALID_UNIT(String.format("로또 구입 금액이 %d원 단위여야 합니다.", LOTTO_MONEY_UNIT)),

    INVALID_SIZE(String.format("로또 번호의 개수는 %d개여야 합니다.", LOTTO_NUMBER_SIZE)),
    INVALID_RANGE(String.format("로또 번호가 %d - %d의 범위여야 합니다.", LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE)),

    INVALID_BONUS_NUMBER("보너스 번호는 숫자여야 합니다."),
    INVALID_BONUS_DISTINCT(String.format("보너스 번호가 당첨 번호 %d개와 중복되지 않아야 합니다.", LOTTO_NUMBER_SIZE));

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_SUFFIX = " 다시 입력해 주세요.";
    private final String message;

    ErrorHandler(String message) {
        this.message = ERROR_PREFIX + message + ERROR_SUFFIX;
    }

    public IllegalArgumentException getException() {
        return new IllegalArgumentException(message);
    }
}
