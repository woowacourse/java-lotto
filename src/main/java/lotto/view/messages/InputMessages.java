package lotto.view.messages;

public enum InputMessages {
    REQUEST_PURCHASE_MONEY("구입금액을 입력해주세요."),
    REQUEST_LAST_WIN_LOTTO_NUMBERS("지난 주 당첨 번호를 입력해 주세요."),
    REQUEST_LAST_WIN_BONUS_BALL("보너스 볼을 입력해 주세요.");
    private final String message;

    InputMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
