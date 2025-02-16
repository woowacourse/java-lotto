package constans;

public enum OutputMessage {

    // ConsoleInputView
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("\n지난 주 당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 볼을 입력해 주세요."),

    // ConsoleOutputView
    LOTTO_PURCHASE_COUNT("%d개를 구매했습니다.\n"),
    LOTTO_WINNING_RESULT_TITLE("\n당첨 통계\n---------"),
    LOTTO_WINNING_RESULT_MATCH("%d개 일치%s(%d원)- %d개\n"),
    LOTTO_WINNING_RESULT_BONUS_BALL(", 보너스 볼 일치"),
    LOTTO_REVENUE("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n"),
    LOTTO_REVENUE_DAMAGE("손해"),
    LOTTO_REVENUE_PROFIT("이익이");


    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
