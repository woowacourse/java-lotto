package view;

public enum DisplayConstants {
    PURCHASE_PRICE_PROMPT("구입금액을 입력해 주세요."),
    WINNING_NUMBER_PROMPT("지난 주 당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_PROMPT("보너스 볼을 입력해 주세요."),

    SHOW_BUY_COUNT("%d개를 구매했습니다."),
    SHOW_STATISTICS("당첨 통계"),
    SHOW_DELIMITER("---------"),

    SHOW_RESULT_RANK5("3개 일치 (5000원)- "),
    SHOW_RESULT_RANK4("4개 일치 (50000원)- "),
    SHOW_RESULT_RANK3("5개 일치 (1500000원)- "),
    SHOW_RESULT_RANK2("5개 일치, 보너스 볼 일치(30000000원) - "),
    SHOW_RESULT_RANK1("6개 일치 (2000000000원)- "),

    SHOW_PRIZE_RATE("총 수익률은 %.2f입니다.");

    private final String format;

    DisplayConstants(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public String format(Object... args) {
        return String.format(format, args);
    }
}