package lotto.view.inputview;

import lotto.domain.LottoAmount;
import lotto.utils.InputUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceParser {
    private static final String ERROR_NULL_OR_NO_INPUT = "입력이 없습니다.";
    private static final String PATTERN_ALLOWED_LAST_CHARACTER = "^([0-9]*)원$";
    private static final int REMOVED_INPUT_LAST_CHARACTER = 1;

    public static LottoAmount getLottoAmount(String inputPrice) {
        return new LottoAmount(InputUtils.toInts(getValidInputPrice(inputPrice)));
    }

    private static String getValidInputPrice(String inputPrice) {
        checkNullEmpty(inputPrice);

        return getCheckedPatternInput(inputPrice);
    }

    private static void checkNullEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new NullPointerException(ERROR_NULL_OR_NO_INPUT);
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
