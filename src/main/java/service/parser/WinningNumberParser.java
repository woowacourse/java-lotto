package service.parser;

import common.constant.NumberConstants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import validator.ErrorMessages;
import validator.Validator;

public class WinningNumberParser {
    public static List<Integer> parseWinningNumbers(String input) {
        Validator.validateEmptyInput(input); // 공백 검사

        List<String> rawNumbers = Arrays.asList(input.split(","));

        validateNotNumber(rawNumbers);
        List<Integer> numbers = rawToIntegers(rawNumbers);
        validateLottoNumberCount(numbers);
        validateDuplicate(numbers);
        validateOutOfRange(rawNumbers, numbers);

        return numbers;
    }

    private static void validateOutOfRange(List<String> rawNumbers, List<Integer> numbers) {
        for (int idx = 0; idx < rawNumbers.size(); idx++) {
            Validator.validateLottoNumberRange(numbers.get(idx));
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (isDuplicates(numbers)) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_EXIST.getMessage());
        }
    }

    public static <T> boolean isDuplicates(List<T> list) {
        if (list == null || list.isEmpty()) {
            return false; // null 또는 빈 리스트는 중복이 없다고 간주
        }

        Set<T> set = new HashSet<>(list);
        return set.size() != list.size();
    }

    private static void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != NumberConstants.LOTTO_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    private static List<Integer> rawToIntegers(List<String> rawNumbers) {
        return rawNumbers.stream()
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateNotNumber(List<String> rawNumbers) {
        for (int i = 0; i < rawNumbers.size(); i++) {
            Validator.validateNumber(rawNumbers.get(i));
        }
    }
}