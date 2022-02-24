package view;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class LottoNumbersParser extends Parser<List<Integer>> {
    private static final String REGEX_DELIMITER = ",";
    public static final String REGEX_BEGINNING = "^";
    private static final String REGEX_PREFIX = "\\s*(";
    public static final String REGEX_END = "$";
    private static final String REGEX_SUFFIX = ")\\s*";
    private static final String REGEX_OR = "|";
    private static final String REGEX_ONE_TO_NINE = "[1-9]";
    private static final String REGEX_TEN_TO_THIRTY_NINE = "[1-3][0-9]";
    private static final String REGEX_FORTY_TO_FORTY_FIVE = "4[0-5]";
    private static final String REGEX_LOTTO_NUMBER_FORMAT =
            REGEX_BEGINNING + numberRegex() + "(\\s*," + numberRegex() + "){5}" + REGEX_END;
    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호는 반드시 6개의 숫자여야 합니다.";

    public LottoNumbersParser() {
        super(REGEX_LOTTO_NUMBER_FORMAT, INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }

    private static String numberRegex() {
        return new StringBuilder(REGEX_PREFIX)
                .append(REGEX_ONE_TO_NINE).append(REGEX_OR)
                .append(REGEX_TEN_TO_THIRTY_NINE).append(REGEX_OR)
                .append(REGEX_FORTY_TO_FORTY_FIVE).append(REGEX_SUFFIX)
                .toString();
    }

    @Override
    protected List<Integer> convert(String text) {
        List<String> tokens = splitNumbers(text);
        List<String> trimNumbers = trimNumbers(tokens);
        List<Integer> intNumbers = toInts(trimNumbers);
        return intNumbers;
    }

    private List<String> splitNumbers(String numbers) {
        return List.of(numbers.split(REGEX_DELIMITER));
    }

    private List<String> trimNumbers(List<String> numbers) {
        return numbers.stream()
                .map(String::trim)
                .collect(toList());
    }

    private List<Integer> toInts(List<String> numbers) {
        return numbers.stream()
                .map(Integer::valueOf)
                .collect(toList());
    }
}
