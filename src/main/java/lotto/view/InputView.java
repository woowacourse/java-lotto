package lotto.view;

public class InputView {
    public static boolean isUnderK(int payment) {
        if (payment < 1000) {
            return false;
        }
        return true;
    }
}
