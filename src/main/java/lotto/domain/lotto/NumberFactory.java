package lotto.domain.lotto;

public class NumberFactory {

    public static Number valueOf(String text) {
        return new Number(toInt(text));
    }

    private static int toInt(final String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자여야 합니다.");
        }
    }
}
