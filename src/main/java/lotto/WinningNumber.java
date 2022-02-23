package lotto;

public class WinningNumber {
    private static final String ERROR_TYPE = "[ERROR] 당첨 번호는 숫자로만 입력해주세요";
    private static final String ERROR_BOUND = "[ERROR] 당첨 번호는 1 이상 45 이하로 입력해주세요";
    private static final int MIN_WINNING_NUMBER = 1;
    private static final int MAX_WINNING_NUMBER = 45;

    private final int number;

    private WinningNumber(int number) {
        checkBound(number);
        this.number = number;
    }

    private void checkBound(int number) {
        if (number < MIN_WINNING_NUMBER || number > MAX_WINNING_NUMBER) {
            throw new IllegalArgumentException(ERROR_BOUND);
        }
    }

    public static WinningNumber from(String input) {
        try {
            return new WinningNumber(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }
}
