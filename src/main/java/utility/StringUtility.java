package utility;

public class StringUtility {

    private static final String IS_NUMBER_PATTERN = "^\\d+$";

    public static boolean isNumber(String string) {
        return string.matches(IS_NUMBER_PATTERN);
    }
}
