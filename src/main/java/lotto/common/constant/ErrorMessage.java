package lotto.common.constant;

import static lotto.common.constant.BusinessRule.*;

public enum ErrorMessage {
    ERROR_LOTTO_NUMBER_RANGE("로또 번호는 " + LOTTO_MINIMUM + "에서 " + LOTTO_MAXIMUM + "사이여야 합니다."),
    ERROR_INCORRECT_LOTTO_SIZE("로또 번호는 " + LOTTO_SIZE + "개여야 합니다"),
    ERROR_MONEY_LESS_THEN_STANDARD("로또를 구매하려면 " + LOTTO_PRICE + "원이상 필요합니다."),
    ERROR_NOT_DIVIDED_BY_STANDARD("돈이 " + LOTTO_PRICE + "원으로 나누어떨어지지 않습니다"),
    ERROR_DUPLICATED_BONUS_NUMBER("보너스 번호가 중복됩니다."),
    ERROR_NOT_NUMBER_TYPE("숫자가 아닙니다.");

    private static final String PREFIX = "[ERROR]";

    private final String value;

    ErrorMessage(String value) {
        this.value = PREFIX + " " + value;
    }

    public String getMessage() {
        return value;
    }

}
