package constant;

public enum InputViewMessage {

    LOTTO_PURCHASE_GUIDANCE("구입금액을 입력해 주세요."),
    LOTTO_GUIDANCE("지난 주 당첨 번호를 입력해 주세요."),
    BONUS_GUIDANCE("보너스 볼을 입력해 주세요."),
    ;

    private final String message;

    InputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
