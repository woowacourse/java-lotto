package view;

public enum Output {

    PURCHASE_MESSAGE("%d개를 구매했습니다."),

    RESULT_TITLE_MESSAGE("당첨 통계"),
    SEPARATE_LINE("---------"),
    FIFTH_MESSAGE("3개 일치 (5000원)- %d개"),
    FOURTH_MESSAGE("4개 일치 (50000원)- %d개"),
    THIRD_MESSAGE("5개 일치 (1500000원)- %d개"),
    SECOND_MESSAGE("5개 일치, 보너스 볼 일치(30000000원) - %d개"),
    FIRST_MESSAGE("6개 일치 (2000000000원)- %d개"),

    PROFIT_MESSAGE("총 수익률은 %.2f입니다.");

    private static String NEW_LINE = System.lineSeparator();

    private String message;

    Output(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message + NEW_LINE;
    }
}
