package lotto.view.inputview;

import lotto.utils.InputUtils;
import lotto.utils.NullCheckUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceParser {
    private static final String PATTERN_ALLOWED_LAST_CHARACTER = "^([0-9]*)원$";
    private static final int REMOVED_INPUT_LAST_CHARACTER = 1;
    private static final String ERROR_NO_INPUT = "입력이 없습니다.";

    public static Integer getPurchasePrice(String inputPrice) {
        return InputUtils.toInts(getValidInputPrice(inputPrice));
    }

    private static String getValidInputPrice(String inputPrice) {
        NullCheckUtil.checkNull(inputPrice);
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
}
