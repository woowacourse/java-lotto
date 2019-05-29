package lotto.view.inputview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceParser {

    private static final String ERROR_NULL_OR_NO_INPUT = "입력이 없습니다.";
    private static final String ERROR_MINIMUM_LIMIT_PRICE = "구매 금액은 1000원 이상입니다.";
    private static final String ERROR_PRICE_UNIT = "구매 금액은 1,000원 단위 입니다.";
    private static final String PATTERN_ALLOWED_LAST_CHARACTER = "^([0-9]*)원$";
    private static final int REMOVED_INPUT_LAST_CHARACTER = 1;
    private static final int MINIMUM_LIMIT_PRICE = 1000;
    private static final int PRICE_UNIT = 1000;
    private static final int DIVISIBLE = 0;

    public static int getLottoAmount(String input) {
        int price = toInts(getValidInput(input));
        checkValidPrice(price);

        return calculateAmount(price);
    }

    private static String getValidInput(String input) {
        checkNullEmpty(input);
        return getCheckedPatternInput(input);
    }

    private static void checkNullEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new NullPointerException(ERROR_NULL_OR_NO_INPUT);
        }
    }

    private static String getCheckedPatternInput(String input) {
        Pattern pattern = Pattern.compile(PATTERN_ALLOWED_LAST_CHARACTER);
        Matcher matcher = pattern.matcher(input);
        String checkedInput = input;

        if (matcher.find()) {
            checkedInput = matcher.group(REMOVED_INPUT_LAST_CHARACTER);
        }

        return checkedInput;
    }

    private static int toInts(String input) {
        return Integer.parseInt(input);
    }

    private static void checkValidPrice(int price) {
        checkMinimumLimit(price);
        checkDivisiblePriceUnit(price);
    }

    private static void checkMinimumLimit(int price) {
        if (price < MINIMUM_LIMIT_PRICE) {
            throw new IllegalArgumentException(ERROR_MINIMUM_LIMIT_PRICE);
        }
    }

    private static void checkDivisiblePriceUnit(int price) {
        if (price % PRICE_UNIT != DIVISIBLE) {
            throw new IllegalArgumentException(ERROR_PRICE_UNIT);
        }
    }

    private static int calculateAmount(int price) {
        return price / PRICE_UNIT;
    }
}
