package lotto.view.messages;

public enum InputMessages {

    PURCHASE_MONEY_INPUT_REQUEST("구입금액을 입력해주세요."),
    WINNING_LOTTO_LINE_INPUT_REQUEST("\n지난 주 당첨 번호를 입력해 주세요."),
    BONUS_BALL_INPUT_REQUEST("보너스 볼을 입력해 주세요.");

    private final String message;

    InputMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
