package util;

import static constant.ExceptionMessages.DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE;

import domain.LottoNumber;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumberValidator {

    public static int validateAndParseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public static void validateLottoNumberRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static void validateNoDuplicates(List<LottoNumber> numbers) {
        Set<LottoNumber> numbersSet = new HashSet<>(numbers);

        if (numbers.size() != numbersSet.size()) {
            throw new IllegalArgumentException(DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public static void validateNoDuplicateInList(int target, List<Integer> nums) {
        if (nums.stream().anyMatch(num -> num == target)) {
            throw new IllegalArgumentException(NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}
