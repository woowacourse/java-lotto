package utils;

public class ExceptionMessage {

    public static final String NOT_ENOUGH_MONEY = "구입 금액은 1000원 미만일 수 없습니다.";
    public static final String NOT_1000_UNIT = "구입 금액은 1000원 단위여야 합니다.";

    public static final String LOTTO_NUMBER_OUT_OF_BOUND = "로또번호는 1~45 사이값으로 생성할 수 있습니다";
    public static final String LOTTO_SIZE_IS_NOT_SIX = "로또 사이즈는 6이여야 합니다.";

    public static final String LOTTO_AND_BONUS_BALL_DUPLICATION = "당첨 번호와 보너스 볼은 중복될 수 없다.";

    public static final String MANUAL_LOTTO_COUNT_IS_NOT_UNDER_ZERO = "수동으로 구매할 로또 수는 음수를 입력할 수 없습니다.";
    public static final String MANUAL_LOTTO_PURCHASE_MONEY_EXCESS_TOTAL_MONEY = "수동 로또 구매 금액이 구입 금액을 초과하였습니다.";

    public static final String MANUAL_LOTTO_GENERATE_LIMIT = "자동 로또 생성기에서는 수동 로또 생성을 할 수 없습니다.";
    public static final String AUTO_LOTTO_GENERATE_LIMIT = "수동 로또 생성기에서는 자동 로또 생성을 할 수 없습니다.";


}