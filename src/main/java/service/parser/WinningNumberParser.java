package service.parser;

import static common.constant.NumberConstants.WINNING_NUMBER_COUNT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import validator.ErrorMessages;
import validator.Validator;

public class WinningNumberParser {
    private static final String COMMA = ",";

    public static List<Integer> parseWinningNumbers(String input) {
        Validator.validateEmptyInput(input); // 공백 검사
        List<String> rawNumbers = splitAndCleanByDelimiter(input, COMMA);
        validateNotNumber(rawNumbers);
        List<Integer> numbers = parseToInt(rawNumbers);

        validateLottoNumberCount(numbers);
        validateDuplicate(numbers);
        for (int number : numbers) {
            Validator.validateLottoNumberRange(number);
        }
        return numbers;
    }

    public static List<String> splitAndCleanByDelimiter(String input, String delimiter) {
        List<String> splitByComma = Arrays.asList(input.split(delimiter));
        return splitByComma.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private static void validateNotNumber(List<String> rawNumbers) {
        for (String rawNumber : rawNumbers) {
            Validator.validateNumber(rawNumber);
        }
    }

    private static List<Integer> parseToInt(List<String> rawNumbers) {
        return rawNumbers.stream()
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessages.LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_EXIST.getMessage());
        }
    }
}