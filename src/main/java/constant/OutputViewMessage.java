package constant;

public enum OutputViewMessage {

    LOTTO_PURCHASE_GUIDANCE("구입금액을 입력해 주세요."),
    WINNING_LOTTO_GUIDANCE("지난 주 당첨 번호를 입력해 주세요."),
    WINNING_BONUS_GUIDANCE("보너스 볼을 입력해 주세요."),

    LOTTO_PURCHASE_RESULT("%d개 구매했습니다."),

    WINNING_STATISTIC_TITLE("당첨 통계"),
    WINNING_STATISTICS_SEPARATOR("---------"),
    FIFTH_PLACE_STATISTICS("3개 일치 (5,000원) - %d"),
    FOUR_PLACE_STATISTICS("4개 일치 (50,000원) - %d"),
    THIRD_PLACE_STATISTICS("5개 일치 (1,500,000원) - %d"),
    SECOND_PLACE_STATISTICS("5개 일치, 보너스 볼 일치 (30,000,000원) - %d"),
    FIRST_PLACE_STATISTICS("6개 일치 (2,000,000,000원) - %d"),

    TOTAL_PROFIT_RATE_RESULT("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)"),
    ;

    private final String message;

    OutputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int arg) {
        return String.format(message, arg);
    }

    public String getMessage(double arg1, String arg2) {
        return String.format(message, arg1, arg2);
    }
}
