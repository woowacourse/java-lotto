package lotto.utils;

public class Validation {

    public static final int START = 1;
    public static final int END = 45;

    public static Boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

}
