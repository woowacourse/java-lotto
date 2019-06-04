package lotto.view;

public enum ConsoleMessages {
    BUY_MONEY("구입금액을 입력해 주세요."),
    MANUAL_COUNT("수동으로 구매할 로또 수를 입력해 주세요."),
    MANUAL_NUMBER("수동으로 구매할 번호를 입력해 주세요."),
    WINNING_NUMBER("지난 주 당첨 번호를 입력해 주세요."),
    WINNING_BONUS("보너스 볼을 입력해 주세요.");

    private final String message;

    private ConsoleMessages(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
