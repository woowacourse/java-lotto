package lotto.util;

public class StringUtil {
    private static final String SPLIT_DELIMITER = ",";
    private static final String REPLACE_DELIMITER = "";
    private static final String REPLACE_STRING = " ";

    public static String[] parseString(String userInput) {
        return userInput.replaceAll(REPLACE_STRING, REPLACE_DELIMITER).split(SPLIT_DELIMITER);
    }
}
