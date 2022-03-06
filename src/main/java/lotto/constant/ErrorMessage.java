package lotto.constant;

public enum ErrorMessage {

    ERROR_LOTTO_DUPLICATE_OR_WRONG_SIZE("[ERROR] 로또 번호는 중복되지 않은 6개의 숫자여야합니다."),
    ERROR_LOTTO_MACHINE_NON_PURCHASABLE("[ERROR] 구입 금액으로 살 수 있는 수량이어야 합니다."),
    ERROR_LOTTO_NUMBER_WRONG_RANGE("[ERROR] 로또 번호는 1~45 사이의 숫자만 가능합니다"),
    ERROR_PURCHASE_AMOUNT_WRONG_UNIT("[ERROR] 구입금액은 1000원 단위여야 합니다"),
    ERROR_WINNING_LOTTO_DUPLICATE("[ERROR] 보너스 볼은 당첨 번호와 중복될 수 없습니다."),
    ERROR_STRING_CONVERTER_NULL_OR_BLANK("[ERROR] 입력값은 공백일 수 없습니다."),
    ERROR_STRING_CONVERTER_NOT_NUMBER("[ERROR] 입력값은 숫자여야합니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
