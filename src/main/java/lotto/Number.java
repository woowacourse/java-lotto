package lotto;

public class Number {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    public Number(int number) {
        validateRangeOfNumber(number);
    }

    private void validateRangeOfNumber(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1이상 45이하이어야 한다.");
        }
    }
}
