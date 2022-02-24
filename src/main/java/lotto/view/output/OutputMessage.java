package lotto.view.output;

public enum OutputMessage {

    REQUEST_CREDIT_MONEY("구입금액을 입력해주세요."),
    REQUEST_WINNING_NUMBERS("지난 주 당첨 번호를 입력해 주세요."),
    REQUEST_BONUS_NUMBER("보너스 볼을 입력해 주세요."),

    EMPTY_STRING(""),
    TITLE_OF_ANALYSIS("당첨 통계"),
    DIVIDING_LINE("---------"),

    TICKET_COUNT_FORMAT("%d개를 구매했습니다."),
    PROFIT_RAGE_FORMAT("총 수익륭은 %.2f입니다.");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
