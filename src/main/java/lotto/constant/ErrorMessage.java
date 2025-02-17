package lotto.constant;

import static lotto.constant.Limit.LOTTO_UNIT_PRICE;
import static lotto.constant.Limit.MAX_PURCHASE_AMOUNT;
import static lotto.constant.Limit.MIN_LOTTO_NUMBER;
import static lotto.constant.Limit.MAX_LOTTO_NUMBER;
import static lotto.constant.Limit.LOTTO_SIZE;


public enum ErrorMessage {
    PURCHASE_AMOUNT_DIVIDE("구입금액은 %d원으로 나누어져야 합니다.".formatted(LOTTO_UNIT_PRICE.getValue())),
    PURCHASE_AMOUNT_RANGE("구입금액은 양수여야 합니다."),
    PURCHASE_AMOUNT_MAX("구입금액은 최대 %d원까지입니다.".formatted(MAX_PURCHASE_AMOUNT.getValue())),
    LOTTO_NUMBER_RANGE("로또 번호는 %d ~ %d 사이여야 합니다.".formatted(MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue())),
    LOTTO_NUMBER_SIZE("%d개의 고유한 번호를 입력해야 합니다.".formatted(LOTTO_SIZE.getValue())),
    LOTTO_NUMBER_DUPLICATE("당첨 번호와 보너스 번호는 중복될 수 없습니다.")
    ;

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
