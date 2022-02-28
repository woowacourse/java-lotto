package utils;

import java.util.List;

public class Messages {

    public static final String BUY_MESSAGE = "개를 구매했습니다.";
    public static final String RESULT_START_MESSAGE =
        "당첨 통계" + System.lineSeparator() + "---------";
    public static final String RESULT_RANK_MESSAGE = "%d개 일치%s(%d원)- %d개" + System.lineSeparator();
    public static final String SAME_BONUS_MESSAGE = ", 보너스 볼 일치";
    public static final String PROFIT_MESSAGE =
        "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해%s라는 의미임)" + System.lineSeparator();
    public static final String NO_MESSAGE = " 아니";

    public static final String NUM_ERROR_MESSAGE = "숫자를 입력해주세요.";
    public static final String LOTTO_MANUAL_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String LOTTO_MANUAL_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    public static final String MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LOTTO_NUMBER_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static final String ERROR_PREFIX = "[ERROR] - ";
    public static final String LOTTO_NUMBER_INPUT_PATTERN_ERROR_MESSAGE = "로또 번호는 1, 2, 3, 4, 5, 6 과 같은 형태로 입력하여야 합니다.";
    public static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1에서 45사이의 수여야 합니다.";
    public static final String MONEY_OVER_THOUSANDS_ERROR_MESSAGE = "로또 구입 금액은 1000원 이상이어야 합니다.";
    public static final String MONEY_DIVIDE_ERROR_MESSAGE = "로또 구입 금액은 1000 단위여야 합니다.";
    public static final String COUNT_OVER_MONEY_ERROR_MESSAGE = "로또 금액을 넘는 수의 로또를 구매할 수 없습니다.";
    public static final String BONUS_DUPLICATED_ERROR_MESSAGE = "보너스 번호는 로또 번호와 중복될 수 없습니다.";
    public static final String LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다.";
}
