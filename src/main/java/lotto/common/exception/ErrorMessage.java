package lotto.common.exception;

public class ErrorMessage {
    private static final String PREFIX = "[ERROR] ";
    public static final String ERROR_NUMBER_RANGE = PREFIX + "번호는 1에서 45 사이여야 합니다.";
    public static final String ERROR_LOTTO_SIZE = PREFIX + "로또 번호는 6개여야 합니다";
    public static final String ERROR_MONEY_NOT_ENOUGH = PREFIX + "로또를 구매하려면 천 원이상 필요합니다.";
    public static final String ERROR_MONEY_NOT_DIVIDED = PREFIX + "돈이 천 원으로 나누어떨어지지 않습니다.";
    public static final String ERROR_DUPLICATE_WINNING_AND_BONUS = PREFIX + "보너스 번호가 당첨번호와 중복됩니다.";
    public static final String ERROR_BONUS_OUT_OF_RANGE = PREFIX + "보너스 번호의 범위는 1에서 45 사이여야 합니다.";
    public static final String ERROR_NUMBER_NOT_VALID = PREFIX + "유효한 숫자 입력이 아닙니다.";
    public static final String ERROR_LOTTO_NUMBER_DUPLICATE = PREFIX + "당첨 번호에 중복된 번호가 존재합니다.";
    public static final String ERROR_MONEY_NOT_VALID = PREFIX + "구입 금액이 0 이하일 수 없습니다.";


}
