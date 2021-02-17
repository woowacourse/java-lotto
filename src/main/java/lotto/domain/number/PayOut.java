package lotto.domain.number;

public class PayOut {

    private static final int GAME_PRICE = 1000;
    private final Number payOut;

    public PayOut(Number number) {
        validateNegative(number.toInt());
        this.payOut = number;
    }

    public PayOut(int number) {
        this(new Number(number));
    }

    public PayOut(String number) {
        this(new Number(number));
    }

    private void validateNegative(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("입력값이 양수가 아닙니다.");
        }
    }

    public int getGameCount() {
        return payOut.toInt() / GAME_PRICE;
    }

    public int toInt() {
        return payOut.toInt();
    }
}