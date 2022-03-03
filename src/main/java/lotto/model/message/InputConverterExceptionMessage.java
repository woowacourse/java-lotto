package lotto.model.message;

public enum InputConverterExceptionMessage {
    BLANK_ERROR("[Error]: 해당 입력에 빈 값이 존재합니다."),
    NUMBER_ERROR("[Error]: 해당 입력은 숫자여야 합니다.");

    private final String message;

    InputConverterExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
