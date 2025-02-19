package constant.message;

public enum InputMessage {

    LOTTO_PURCHASE_GUIDANCE("구입금액을 입력해 주세요."),
    WINNING_LOTTO_GUIDANCE("지난 주 당첨 번호를 입력해 주세요."),
    WINNING_BONUS_GUIDANCE("보너스 볼을 입력해 주세요."),

    LOTTO_PURCHASE_RESULT("%s개 구매했습니다."),
    ;

    private final String message;

    InputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(final String arg) {
        return String.format(message, arg);
    }
}
