package validator;

import static constant.ExceptionMessages.DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBERS_SIZE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_MANUAL_LOTTOS_COUNT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE;
import static domain.Lotto.LOTTO_NUMBERS_SIZE;
import static domain.LottoNumber.MAXIMUM_NUMBER;
import static domain.LottoNumber.MINIMUM_NUMBER;

import domain.Lotto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberValidators {

    private NumberValidators() {
    }

    public static int validateAndParseNumber(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public static void validateTotalLottoPriceUnit(int num) {
        validateNotNegative(num);
        if (num % Lotto.PRICE > 0) {
            throw new IllegalArgumentException(INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE);
        }
    }

    public static void validateManualLottosCount(int manualsCount, int totalCount) {
        validateNotNegative(manualsCount);
        validateNotNegative(totalCount);
        if (manualsCount > totalCount) {
            throw new IllegalArgumentException(INVALID_MANUAL_LOTTOS_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateNotNegative(int num) {
        if (num < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public static void validateLottoNumberRange(int num) {
        if (num < MINIMUM_NUMBER || num > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static void validateLottoNumbersSize(List<?> list) {
        if (list.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public static <T> void validateNoDuplicates(List<T> values) {
        Set<T> noDuplicateValues = new HashSet<>(values);

        if (values.size() != noDuplicateValues.size()) {
            throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public static <T> void validateNoDuplicateInList(T target, List<T> values) {
        boolean targetExistsInList = values.stream()
                .anyMatch(value -> value == target);

        if (targetExistsInList) {
            throw new IllegalArgumentException(NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}
