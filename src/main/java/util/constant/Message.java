package util.constant;

public class Message {

    /* error message */
    public static final String PRICE_NEGATIVE_ERROR = "금액은 음수가 불가능 합니다.";
    public static final String WINNING_NUMBER_SIZE_ERROR = "당첨번호는 6자리만 가능합니다.";
    public static final String WINNING_NUMBER_DUPLICATE_ERROR = "당첨 번호는 중복될 수 없습니다.";
    public static final String BONUS_NUMBER_DUPLICATE_ERROR = "보너스 숫자는 당첨 번호와 중복될 수 없습니다.";
    public static final String WINNING_NUMBER_RANGE_ERROR = "당첨 번호는 1~50 사이의 값만 가능합니다.";
    public static final String INPUT_NULL_ERROR = "공백 및 null 값은 허용하지 않습니다";
    public static final String INPUT_NON_NUMERIC_ERROR = "숫자가 아닌 입력은 불가능 합니다.";

    /* input message */
    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    /* output message */
    public static final String TOTAL_LOTTO_FORMAT = "%d개를 구매했습니다.\n";
    public static final String STATISTICS_MATCH_FORMAT = "%d개 일치";
    public static final String STATISTICS_BONUS_BALL_FORMAT = ", 보너스 볼 일치";
    public static final String STATISTICS_PRICE_FORMAT = " (%d원)";
    public static final String STATISTICS_FORMAT = "%s- %d개\n";
    public static final String PROFIT_FORMAT = "총 수익률은 %.2f입니다.";
    public static final String STATISTICS_START_MESSAGE = "당첨 통계";
    public static final String DIVIDER_LINE = "---------";
    public static final String PROFIT_DAMAGE_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
}
