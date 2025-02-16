package exception;

public class ExceptionMessage {
    public static final String BLANK_VALUE = "빈 값이 아닌 값을 입력해주세요.";
    public static final String NOT_INTEGER_VALUE = "정수 값을 입력해주세요.";
    public static final String INTEGER_OVERFLOW_VALUE = "10자리 이하의 값을 입력해주세요.";

    public static final String OUT_OF_PURCHASE_AMOUNT_RANGE = "1,000 이상 100,000 이하의 정수를 입력해주세요.";
    public static final String NOT_DIVISIBLE_PURCHASE_AMOUNT = "1,000으로 나누어 떨어지는 값을 입력해주세요.";

    public static final String OUT_OF_LOTTO_NUMBER_RANGE = "1~45 사이의 정수로 입력해주세요.";
    public static final String OUT_OF_LOTTO_SIZE = "6개의 번호를 입력해주세요.";
    public static final String DUPLICATED_LOTTO_NUMBER = "중복 없는 번호를 입력해주세요.";
    public static final String DUPLICATED_BONUS_NUMBER = "보너스 볼은 당첨 번호와 중복되지 않게 입력해주세요.";
}
