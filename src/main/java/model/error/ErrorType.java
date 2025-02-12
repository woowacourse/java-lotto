package model.error;

public enum ErrorType {

    숫자오류("숫자여야합니다."),
    로또숫자범위오류("로또 번호 범위는 1에서 45까지의 숫자여야합니다."),
    로또숫자중복오류("로또 번호들은 중복될 수 없습니다.");

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR]" + this.message;
    }
}
