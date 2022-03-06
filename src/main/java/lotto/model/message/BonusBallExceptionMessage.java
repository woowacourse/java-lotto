package lotto.model.message;

public enum BonusBallExceptionMessage {
    RANGE_ERROR("[Error]: 보너스 볼은 1~45의 숫자만 입력해주세요.");

    private final String message;

    BonusBallExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
