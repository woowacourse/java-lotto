package lotto.view;

public enum OutputMessage {
    // OutputView
    TICKET_COUNT_MESSAGE("%d개를 구매했습니다.%n"),
    RESULT_TITLE_MESSAGE("%n당첨 통계"),
    DIVIDER("---------"),
    RANK_COUNT_MESSAGE_WITH_BONUS("%d개 일치, 보너스 볼 일치(%d원) - %d개%n"),
    RANK_COUNT_MESSAGE("%d개 일치 (%d원)- %d개%n"),
    RETURN_OF_RATE_MESSAGE("총 수익률은 %.2f입니다.%n"),
    EMPTY(""),

    // InputView
    PAYMENT_MESSAGE("구입금액을 입력해 주세요."),
    WINNING_NUMBER_MESSAGE("지난 주 당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_MESSAGE("보너스 볼을 입력해 주세요."),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
