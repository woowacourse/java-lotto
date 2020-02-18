package lotto.view;

public class InputView {
    public static boolean isUnderK(int payment) {
        if (payment < 1000) {
            return false;
        }
        return true;
    }

    public static void checkNumberFormat(String a) {
        try {
            Integer.parseInt(a);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("");
        }
    }

    public static boolean isValueRange(int i) {
        return i >= 1000 && i <= 100000;
    }

    public static boolean isUnitK(int input) {
        return input % 1000 == 0;
    }
}
