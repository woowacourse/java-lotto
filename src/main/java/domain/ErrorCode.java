package domain;

import static domain.Lotto.LOTTO_PRICE;
import static domain.Lotto.MAX_LOTTO_NUMBER;
import static domain.Lotto.MIN_LOTTO_NUMBER;

public enum ErrorCode {
    WINNING_LOTTO_CONTAINS_BONUS_NUMBER("보너스 숫자는 당첨 번호와 중복될 수 없습니다."),
    WINNING_NUMBERS_QUANTITY_NOT_SIX("당첨 번호는 6개 입력해야 합니다."),
    WINNING_NUMBER_NOT_IN_RANGE("당첨 번호는 " + MIN_LOTTO_NUMBER + " ~ " + MAX_LOTTO_NUMBER + "만 가능합니다."),
    WINNING_NUMBER_WRONG_SYNTAX("당첨 번호 입력 양식이 올바르지 않습니다."),
    WINNING_NUMBER_DUPLICATED("당첨 번호는 중복될 수 없습니다."),
    BONUS_NUMBER_WRONG_SYNTAX("보너스 번호 입력 양식이 올바르지 않습니다."),
    PURCHASE_AMOUNT_WRONG_SYNTAX("구입 금액 입력 양식이 올바르지 않습니다."),
    PURCHASE_AMOUNT_NOT_DIVIDED_BY_UNIT("구입 금액은" + LOTTO_PRICE + "원 단위로 가능합니다."),
    PURCHASE_AMOUNT_NOT_IN_RANGE("구입 금액은 " + LOTTO_PRICE + "원 이상부터 가능합니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
