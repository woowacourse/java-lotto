package lotto.util;

public class StringUtils {
    public static final String LOTTO_DELIMITER = "\n";
    private static final String NUMBER_DELIMITER = ",";

    public static String[] splitNumber(String inputWinNumber) {
        return inputWinNumber.split(NUMBER_DELIMITER);
    }

    public static String[] splitLotto(String inputWinNumber) {
        return inputWinNumber.split(LOTTO_DELIMITER);
    }
}
