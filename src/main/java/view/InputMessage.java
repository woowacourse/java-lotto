package view;

public enum InputMessage {
    PRICE("구입금액을 입력해 주세요."),
    WINNING_LOTTO("지난 주 당첨 번호를 입력해 주세요."),
    BONUS("보너스 볼을 입력해 주세요.");

    private String message;

    InputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
