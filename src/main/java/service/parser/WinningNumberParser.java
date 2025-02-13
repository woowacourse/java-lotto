package service.parser;

import constant.NumberConstants;
import constant.RegexConstants;
import java.util.Arrays;
import java.util.List;
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
        for (int idx = 0; idx < rawNumbers.size(); idx++)
            Validator.validateOutOfRange(numbers.get(idx), NumberConstants.LOTTO_NUMBER_START, NumberConstants.LOTTO_NUMBER_END, ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (Validator.isDuplicates(numbers)){
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_EXIST.getMessage());
        }
    }

    private static void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != NumberConstants.LOTTO_COUNT){
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
            Validator.validateInvalidForm(rawNumbers.get(i), RegexConstants.NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());
        }
    }
}