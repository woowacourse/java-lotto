package lotto;

public class WinningNumber implements LottoNumber{
    private static final String ERROR_TYPE = "[ERROR] 당첨 번호는 숫자로만 입력해주세요";

    private final int number;

    private WinningNumber(int number) {
        checkBound(number);
        this.number = number;
    }

    public static WinningNumber from(String input) {
        try {
            return new WinningNumber(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }
}
