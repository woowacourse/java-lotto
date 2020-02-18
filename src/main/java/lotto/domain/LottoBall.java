package lotto.domain;

public class LottoBall {
    private final int number;

    public LottoBall(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("입력값: " + number + ": 범위 이외 숫자");
        }
    }
}
