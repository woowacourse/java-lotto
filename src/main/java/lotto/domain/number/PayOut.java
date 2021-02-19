package lotto.domain.number;

public class PayOut {

    private static final int PAYOUT_MINIMUM = 0;
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
        if (!number.isBiggerThan(PAYOUT_MINIMUM)) {
            throw new IllegalArgumentException("입력값이 양수가 아닙니다.");
        }
    }

    public int getGameCount() {
        return number.unbox() / GAME_PRICE;
    }

    public int unbox() {
        return number.unbox();
    }
}