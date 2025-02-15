package lotto.exception;

public enum ExceptionMessage {

    INVALID_INPUT("올바르지 않은 입력값입니다."),
    MUST_BE_DIVIDE_BY_THOUSAND("금액은 1000으로 나누어 떨어져야 합니다."),
    NOT_ALLOW_NEGATIVE("양수만 입력 해 주세요."),
    OUT_OF_RANGE("범위를 벗어나는 숫자입니다."),
    MUST_NOT_BE_DUPLICATED_LOTTO_NUMBER("로또 번호는 중복될 수 없습니다."),
    MUST_NOT_BE_DUPLICATED_WINNING_NUMBER("당첨 번호는 중복될 수 없습니다."),
    MUST_NOT_BE_DUPLICATED_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    INVALID_LOTTO_SIZE("로또의 갯수가 일치하지 않습니다."),
    INVALID_WINNING_NUMBER_SIZE("로또의 갯수가 일치하지 않습니다.");

    private static final String PREFIX = "[ERROR] ";

    ExceptionMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private final String errorMessage;

    @Override
    public String toString() {
        return PREFIX + errorMessage;
    }
}
