package exception;

public enum ErrorMessage {
    LOTTO_RANGE_ERROR("로또 번호 범위 오류"),
    LOTTO_NUMBER_DUPLICATED_ERROR("로또 번호 중복 오류"),
    LOTTO_SIZE_ERROR("로또 번호 갯수 오류"),
    PRICE_RANGE_ERROR("금액 범위 오류"),
    PRICE_UNIT_ERROR("금액 단위 오류"),
    INPUT_FORMAT_ERROR("입력 형식 오류"),
    NUMBER_TYPE_ERROR("숫자 타입 오류");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
