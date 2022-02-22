package lotto.domain;

public class LottoNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;

    private static final String NUMBER_RANGE_ERROR = "로또 숫자는 " + MIN + " 이상 " + MAX + " 이하의 숫자만 가능합니다.";
    public static final String TYPE_ERROR = "로또 번호는 숫자만 가능합니다.";

    private int number;

    public LottoNumber(String input) {
        int number = convertToInt(input);
        validateNumber(number);
        this.number = number;
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(TYPE_ERROR);
        }
    }

    public int getNumber() {
        return this.number;
    }

    private void validateNumber(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }
}
