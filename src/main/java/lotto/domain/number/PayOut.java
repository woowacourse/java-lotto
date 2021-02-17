package lotto.domain.number;

public class PayOut {

    private static final int GAME_PRICE = 1000;
    private final Number number;


    public PayOut(Number number) {
        validateNegative(number.getValue());
        this.number = number;
    }

    private void validateNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("입력값이 음수 입니다.");
        }
    }

    public int getGameCount() {
        return number.getValue() / GAME_PRICE;
    }

    public Number getNumber() {
        return number.clone();
    }
}