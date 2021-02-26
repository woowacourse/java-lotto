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

    public static Boolean isNumericAndPositive(String value) {
        if (!isNumeric(value)) {
            return false;
        }

        int valueInt = Integer.parseInt(value);

        if (valueInt < 0) {
            return false;
        }

        return true;
    }
}
