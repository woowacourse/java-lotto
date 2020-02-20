package lotto.model;

public class Payment {
    public static int payment = 0;

    public Payment(String input) {
        int payment = isNotNumber(input);
        isValueRange(payment);
        isUnitK(payment);
        this.payment = payment;
    }

    private static int isNotNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자를 입력하세요.");
        }
    }

    private static void isValueRange(int payment) {
        if (!(payment >= 1000 && payment <= 100000)) {
            throw new IllegalArgumentException("범위를 벗어났습니다.");
        }
    }

    private static void isUnitK(int payment) {
        if (!(payment % 1000 == 0)) {
            throw new IllegalArgumentException("천 단위로 입력하세요.");
        }
    }
}
