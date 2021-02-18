package lotto.view.messages;

public enum OutputMessages {

    WINNING_LOTTO_LINE_STATISTICS("\n당첨 통계\n---------"),
    FIFTH_PLACE_RESULT_FORMAT("3개 일치 (5000원)- %d개\n"),
    FORTH_PLACE_RESULT_FORMAT("4개 일치 (50000원)- %d개\n"),
    THIRD_PLACE_RESULT_FORMAT("5개 일치 (1500000원)- %d개\n"),
    SECOND_PLACE_RESULT_FORMAT("5개 일치, 보너스 볼 일치 (30000000원)- %d개\n"),
    FIRST_PLACE_RESULT_FORMAT("6개 일치 (2000000000원)- %d개\n"),
    OUTPUT_PROFIT_RESULT_FORMAT("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");

    private final String message;

    OutputMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
