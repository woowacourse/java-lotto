package domain.excepetion;

public class AmountExceptionMessage {
    private static final String PREFIX = "[ERROR] ";

    public static final String INVALID_POSITIVE = PREFIX + "양수로 입력해 주세요.";
    public static final String INVALID_INTEGER = PREFIX + "정수로 입력해 주세요.";
    public static final String INVALID_UNIT_PRICE = PREFIX + "구매 금액 단위로 입력해 주세요.";
}
