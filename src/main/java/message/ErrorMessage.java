package message;

public enum ErrorMessage {
    NUMBER_FORMAT("양의 정수만 입력할 수 있습니다."),
    NUMBER_RANGE("1~45 범위 이내여야 합니다."),
    LOTTO_DUPLICATED("로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    LOTTO_NOT_SORTED("로또 번호가 정렬되어 있지 않습니다."),
    MONEY_MINIMUM("돈은 최소 1,000원 이상이어야 합니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
