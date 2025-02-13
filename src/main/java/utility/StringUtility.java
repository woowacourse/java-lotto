package utility;

import java.util.regex.Pattern;
import javax.print.attribute.standard.MediaSize.ISO;

public class StringUtility {
    private static final String IS_NUMBER_PATTERN = "^\\d+$";
    public static boolean isNumber(String string) {
        return string.trim().matches(IS_NUMBER_PATTERN);
    }

}
