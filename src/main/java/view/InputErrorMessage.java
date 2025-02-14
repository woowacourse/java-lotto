package view;

public enum InputErrorMessage {
    INTEGER_REQUIRED("정수를 입력해주세요."),
    NOT_DIVIDED_BY_1000("로또 금액(1000)으로 나누어 떨어지지 않습니다."),
    POSITIVE_INTEGER_REQUIRED("양수가 아닙니다."),
    DUPLICATED("중복된 번호가 존재합니다.");

    private String message;

    InputErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
