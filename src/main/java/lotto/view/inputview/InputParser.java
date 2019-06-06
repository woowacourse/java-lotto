package lotto.view.inputview;

import lotto.utils.NullCheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser {
    private static final String PATTERN_ALLOWED_LAST_CHARACTER = "^([0-9]*)원$";
    private static final int REMOVED_INPUT_LAST_CHARACTER = 1;
    private static final String ERROR_NO_INPUT = "입력이 없습니다.";
    private static final String SPACE = " ";
    private static final String NON_SPACE = "";
    private static final String COMMA = ",";

    public static Integer getPurchasePrice(String inputPrice) {
        return toInts(getValidInputPrice(inputPrice));
    }

    private static String getValidInputPrice(String inputPrice) {
        NullCheckUtil.checkNullInput(inputPrice);
        checkNoInput(inputPrice);

        return getCheckedPatternInput(inputPrice);
    }

    private static void checkNoInput(String inputPrice) {
        if (inputPrice.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NO_INPUT);
        }
    }

    private static String getCheckedPatternInput(String input) {
        Pattern pattern = Pattern.compile(PATTERN_ALLOWED_LAST_CHARACTER);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(REMOVED_INPUT_LAST_CHARACTER);
        }

        return input;
    }

    private static int toInts(String input) {
        return Integer.parseInt(input);
    }

    public static List<Integer> getLottoNum(String inputNumbers) {
        NullCheckUtil.checkNullInput(inputNumbers);
        checkNoInput(inputNumbers);

        return Arrays.stream(inputNumbers.replaceAll(SPACE, NON_SPACE).split(COMMA))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
