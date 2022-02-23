package validator;

import static constant.ExceptionMessages.DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE;
import static constant.LottoConstants.LOTTO_PRICE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberValidators {

    public static int validateAndParseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public static void validateTotalLottoPriceUnit(int num) {
        if (num % LOTTO_PRICE > 0 ) {
            throw new IllegalArgumentException(INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE);
        }
    }

    public static void validateLottoNumberRange(int num) {
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static void validateNoDuplicates(List<Integer> nums) {
        Set<Integer> numsSet = new HashSet<>(nums);

        if (nums.size() != numsSet.size()) {
            throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public static void validateNoDuplicateInList(int target, List<Integer> nums) {
        if (nums.stream().anyMatch(num -> num == target)) {
            throw new IllegalArgumentException(NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}


