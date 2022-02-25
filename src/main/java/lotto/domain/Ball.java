package lotto.domain;

public class Ball{
    private static final String NUMBER_MATCHES = "-?[0-9]+";
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    private static final String ERROR_ONLY_NUMBER = "숫자를 입력해주세요!";
    private static final String ERROR_LOTTO_NUMBER = "로또 숫자가 아닙니다";

    private final int number;

    public Ball(String number) {
        validateBall(number);
        this.number = Integer.parseInt(number);
    }

    private void validateBall(final String value) {
        if (isBlank(value) || !isNumber(value)) {
            throw new IllegalArgumentException(ERROR_ONLY_NUMBER);
        }
        int number = Integer.parseInt(value);
        validateLottoNumber(number);
    }

    private boolean isBlank(final String number) {
        return number == null || number.isEmpty();
    }

    private boolean isNumber(final String value) {
        return value.matches(NUMBER_MATCHES);
    }

    private void validateLottoNumber(final int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            // TODO: 예외 처리 별도 객체 생성
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public boolean equals(Object obj) {
        Ball ball = (Ball)obj;
        return this.number == ball.number;
    }
}
