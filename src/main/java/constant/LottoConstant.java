package constant;

public class LottoConstant {
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    public static final int LOTTO_NUMBERS_SIZE = 6;
    public static final int LOTTO_TICKET_PRICE = 1000;

    public static final String INVALID_LOTTO_NUMBER_RANGE = "번호는 1부터 45 사이여야 합니다.";
    public static final String INVALID_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    public static final String LOTTO_NUMBER_SIZE_NOT_VALID = "로또 번호는 6자리여야 합니다.";
    public static final String LOTTO_NUMBER_DUPLICATE = "로또 번호는 중복되지 않아야 합니다.";
    public static final String INVALID_MANUAL_COUNT = "수동 로또 개수는 0 이상이어야 합니다.";
    public static final String INVALID_MANUAL_COUNT_WITH_MONEY = "입력 금액으로 살 수 없습니다.";
}
