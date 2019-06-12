package lotto.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
    private static final String SPACE = " ";
    private static final String BLANK = "";

    public static List<String> convertToList(String text, String separator) {
        String[] splitedText = text.replaceAll(SPACE, BLANK).split(separator);
        if(StringUtils.isBlank(splitedText[0])) {
            return new ArrayList<>();
        }
        return Arrays.asList(splitedText);
    }
}
