package lotterymachine.view;

public enum ErrorMessage {
    IS_NOT_NUMBER_ERROR_MESSAGE("숫자만 입력할 수 있습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
