package lotto.view;

public enum OutputMessage {
    TICKET_COUNT_MESSAGE("%d개를 구매했습니다.%n"),
    RESULT_TITLE_MESSAGE("%n당첨 통계"),
    DIVIDER("---------"),
    RANK_COUNT_MESSAGE_WITH_BONUS("%d개 일치, 보너스 볼 일치(%d원) - %d개%n"),
    RANK_COUNT_MESSAGE("%d개 일치 (%d원)- %d개%n"),
    RETURN_OF_RATE_MESSAGE("총 수익률은 %.2f입니다.%n"),
    EMPTY(""),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
