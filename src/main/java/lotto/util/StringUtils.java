package lotto.util;

public class StringUtils {
    private static final String DELIMITER = ",";

    public static String[] split(String inputWinNumber) {
        return inputWinNumber.split(DELIMITER);
    }
}
