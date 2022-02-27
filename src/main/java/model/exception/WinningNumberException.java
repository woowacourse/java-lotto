package model.exception;

public enum WinningNumberException {
    NUMBER_ERROR("[Error]: 당첨 번호는 숫자여야 합니다."),
    BLANK_ERROR("[Error]: 당첨 번호를 입력하세요."),
    RANGE_ERROR("[Error]: 당첨 번호는 1~45 숫자여야 합니다."),
    SIZE_ERROR("[Error]: 당첨 번호는 6개의 숫자여야 합니다."),
    REDUPLICATION_ERROR("[Error]: 당첨 번호는 중복이 있으면 안됩니다"),
    REDUPLICATION_BONUS_BALL_ERROR("[Error]: 당첨 번호와 보너스 볼이 중복됩니다.");

    private final String massage;

    WinningNumberException(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
