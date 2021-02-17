package lotto.domain.number;

public class PayOut {

    private static final int GAME_PRICE = 1000;
    private final Number payOut;


    public PayOut(Number number) {
        validateNegative(number.getValue());
        this.payOut = number;
    }

    private void validateNegative(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("입력값이 양수가 아닙니다.");
        }
    }

    public int getGameCount() {
        return payOut.getValue() / GAME_PRICE;
    }

    public Number getPayOut() {
        return payOut.clone();
    }
}