package lotto.exception;

public enum ErrorMessage {

    INVALID_INPUT("올바르지 않은 입력값입니다."),
    MUST_BE_DIVIDE_BY_THOUSAND("금액은 1000으로 나누어 떨어져야 합니다."),
    NOT_ALLOW_NEGATIVE("음수는 입력할 수 없습니다."),
    OUT_OF_RANGE("범위를 벗어나는 숫자입니다."),
    MUST_NOT_BE_DUPLICATED("로또 번호는 중복될 수 없습니다."),
    SIZE_ERROR("로또의 갯수가 일치하지 않습니다.");

    private static final String PREFIX = "[ERROR] ";

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private final String errorMessage;

    @Override
    public String toString() {
        return PREFIX + errorMessage;
    }
}
