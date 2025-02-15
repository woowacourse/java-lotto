package constant;

public enum OutputViewMessage {

    LOTTO_PURCHASE_RESULT("%d개 구매했습니다."),

    WINNING_STATISTIC_TITLE("당첨 통계"),
    WINNING_STATISTICS_SEPARATOR("---------"),
    WINNING_STATISTIC_ENTRY("%s - %d개"),

    TOTAL_PROFIT_RATE_RESULT("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)"),
    ;

    private final String message;

    OutputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
