package lotto.domain.number;

public class PayOut {

    private static final int GAME_PRICE = 1000;

    private final Number number;

    private PayOut(Number number) {
        validateNegative(number);
        this.number = number;
    }

    public static PayOut valueOf(String number) {
        return new PayOut(Number.valueOf(number));
    }

    private static void validateNegative(Number number) {
        if (!number.isBiggerThan(Number.valueOf(0))) {
            throw new IllegalArgumentException("입력값이 양수가 아닙니다.");
        }
    }

    public int getGameCount() {
        return number.toInt() / GAME_PRICE;
    }

    public int toInt() {
        return number.toInt();
    }
}