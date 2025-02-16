package domain.excepetion;

public class LottoExceptionMessage {
    private static final String PREFIX = "[ERROR] ";

    public static final String INVALID_POSITIVE = PREFIX + "양수로 입력해 주세요.";
    public static final String INVALID_INTEGER = PREFIX + "정수로 입력해 주세요.";
    public static final String INVALID_UNIT_PRICE = PREFIX + "구매 금액 단위로 입력해 주세요.";
    public static final String INVALID_RANGE = PREFIX + "범위에 맞는 로또 번호를 입력하세요.";
    public static final String INVALID_FORMAT = PREFIX + "정해진 형식으로 입력해 주세요.";

    public static final String DUPLICATED_NUMBER = PREFIX + "로또 번호는 중복될 수 없습니다.";
}
