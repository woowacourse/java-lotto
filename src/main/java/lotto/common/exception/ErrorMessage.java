package lotto.common.exception;

public class ErrorMessage {
    private static final String PREFIX = "[ERROR]";
    public static final String ERROR_NUMBER_RANGE = PREFIX + "로또 번호는 1에서 45 사이여야 합니다.";
    public static final String ERROR_LOTTO_SIZE = PREFIX + "로또 번호는 6개여야 합니다";
    public static final String ERROR_MONEY_ZERO = PREFIX + "로또를 구매하려면 천 원이상 필요합니다.";
    public static final String ERROR_MONEY_NOT_DIVIDED = PREFIX + "돈이 천 원으로 나누어떨어지지 않습니다.";
    public static final String ERROR_CONTAINS_BONUS = PREFIX + "보너스 번호가 중복됩니다.";
    public static final String ERROR_NOT_NUMBER = PREFIX + "숫자가 아닙니다.";
}
