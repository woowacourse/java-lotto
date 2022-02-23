package lotto;

public class BonusNumber implements LottoNumber {
    private static final String ERROR_TYPE = "[ERROR] 보너스 번호는 숫자로만 입력해주세요";

    private final int number;

    private BonusNumber(int number) {
        checkBound(number);
        this.number = number;
    }

    public static BonusNumber from(String input) {
        try {
            return new BonusNumber(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }
}
