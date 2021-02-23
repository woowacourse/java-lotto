package lotto.utils;

public class Validation {

    public static Boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }
}
