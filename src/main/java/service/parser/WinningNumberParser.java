package service.parser;

import static validator.ErrorMessages.LOTTO_NUMBER_COUNT;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import validator.ErrorMessages;
import validator.Validator;

public class WinningNumberParser {

    private static final String NUMBER_ONLY_REGEX = "^[0-9]+$"; // 숫자만
    private static final String MULTIPLE_TEMPLATE = "%s{2,}"; // 연속된 구분자
    private static final String EDGE_TEMPLATE = "^%s|%s$"; // 앞뒤 구분자
    private static final String EMPTY_STRING = ""; // 빈문자열

    // 한 종류의 구분자만 사용되는 경우, 해당 구분자를 정리
    public static String cleanConsecutiveDelimiters(String input, String delimiter) {
        String escapedDelimiter = Pattern.quote(delimiter); // 특수 문자 처리를 위해 escape
        String consecutiveDelimitersRegex = String.format(MULTIPLE_TEMPLATE, escapedDelimiter); // 연속된 구분자 표현식
        String leadingTrailingDelimiterRegex =
                String.format(EDGE_TEMPLATE, escapedDelimiter, escapedDelimiter); // 앞뒤 구분자 표현식

        return input.replaceAll(consecutiveDelimitersRegex, delimiter) // 연속된 구분자를 하나로 변경
                .replaceAll(leadingTrailingDelimiterRegex, EMPTY_STRING); // 앞뒤 구분자 제거
    }

    // 구분자로 해당 문자열 분리 후 문자열 리스트로 반환
    public static List<String> splitStringByDelimiter(String input, String delimiter) {
        return Arrays.asList(input.split(delimiter));
    }

    public static List<Integer> parseWinningNumbers(String input) {
        Validator.validateEmptyInput(input); // 공백 검사

        String cleanInput = cleanConsecutiveDelimiters(input, ",");
        List<String> rawNumbers = splitStringByDelimiter(cleanInput, ",");

        // TODO: 유효성 검사
        if (rawNumbers.size() != 6){
            throw new IllegalArgumentException(LOTTO_NUMBER_COUNT.getMessage());
        }
        for (int i = 0; i < rawNumbers.size(); i++) {
            Validator.checkInvalidForm(rawNumbers.get(i), NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());
        }
        Validator.isDuplicates(rawNumbers);

        List<Integer> numbers =  rawNumbers.stream()
                .map(num -> Integer.parseInt(num))
                .toList();

        for (int idx = 0; idx < rawNumbers.size(); idx++)
            Validator.checkOutOfRange(numbers.get(idx), 1, 45, ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());

        return numbers;
    }
}