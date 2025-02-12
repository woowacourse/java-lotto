package utility;

import java.util.regex.Pattern;

public class StringUtility {
    private static final String IS_NUMBER_PATTERN = "^[0-9]+$";
    public static boolean isNumber(String string) {
        return Pattern.matches(IS_NUMBER_PATTERN,string);
    }

}
