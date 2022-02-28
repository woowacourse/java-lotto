package lotto.exception;

public enum BonusBallException {
    BLANK_ERROR("[Error]: 보너스 볼을 입력해주세요."),
    NUMBER_ERROR("[Error]: 보너스 볼은 숫자여야 합니다."),
    RANGE_ERROR("[Error]: 보너스 볼은 1~45의 숫자만 입력해주세요.");

    private final String message;

    BonusBallException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
