package domain.lotto;

public class Ball {

    public static final int MIN_BALL_NUMBER = 1;
    public static final int MAX_BALL_NUMBER = 45;

    private final int number;

    public Ball(final int number) {
        validateRange(number);
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }

    private void validateRange(final int number) {
        if (number < MIN_BALL_NUMBER || number > MAX_BALL_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 " + MIN_BALL_NUMBER + " ~ " + MAX_BALL_NUMBER + "만 가능합니다.");
        }
    }

}
