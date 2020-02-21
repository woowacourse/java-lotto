package lotto.domain.ticket.ball;

public class LottoBallValidator {
    public static final int LOWER_BOUND = 1;
    public static final int UPPER_BOUND = 45;
    private static final String MESSAGE_FOR_INVALID_LOTTO_BALL_NUMBER = "%d: 로또 범위 이외 숫자입니다";

    public static void validateNumber(int number) {
        if (number < LOWER_BOUND || number > UPPER_BOUND) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_INVALID_LOTTO_BALL_NUMBER, number));
        }
    }
}
