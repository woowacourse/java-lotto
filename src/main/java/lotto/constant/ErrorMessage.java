package lotto.constant;

public enum ErrorMessage {

    RANGE_ERROR("1과 45 사이의 수를 입력하셔야 합니다."),
    NUMBER_FORMAT_ERROR("당첨 번호는 숫자를 입력하셔야 합니다."),
    NUMBER_DUPLICATED_ERROR("로또 숫자는 중복될 수 없습니다."),
    BONUS_NUMBER_DUPLICATED_ERROR("보너스 숫자는 당첨 번호와 중복될 수 없습니다."),
    BONUS_NUMBER_FORMAT_ERROR("보너스 숫자는 숫자여야 합니다."),
    NUMBER_LENGTH_ERROR("6자리를 입력하셔야 합니다."),
    PURCHASE_FORMAT_ERROR("구입금액은 숫자여야 합니다."),
    PURCHASE_UNIT_ERROR("구입금액은 천원 단위여야 합니다."),
    PURCHASE_MINIMUM_ERROR("구입금액은 천원부터 입니다."),
    PURCHASE_MAXIMUM_ERROR("10만원까지 구매 가능 합니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
