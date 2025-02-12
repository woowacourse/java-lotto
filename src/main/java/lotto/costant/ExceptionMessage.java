package lotto.costant;

public enum ExceptionMessage {
    DUPLICATED_NUMBERS("중복되는 번호가 있습니다."),
    OUT_OF_RANGE("범위를 벗어난 값입니다."),
    INVALID_NUMBER_COUNT("올바르지 않은 숫자 개수입니다."),
    INVALID_NUMBER_FORMAT("숫자 형식이 아닙니다."),
    INVALID_PURCHASE_AMOUNT("구입 금액은 %d단위 입니다."),
    INVALID_INPUT("유효하지 않은 입력값입니다.");

    private final String content;

    ExceptionMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
