package view;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class LottoNumbersParser extends Parser<List<Integer>> {
    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호는 반드시 6개의 숫자여야 합니다.";

    public LottoNumbersParser() {
        super(regex(), INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }

    private static String regex() {
        return new StringBuilder(REGEX_BEGINNING)
            .append(lottoNumberWithSpacesRegex())
            .append(REGEX_GROUP_BEGINNING)
            .append(REGEX_SPACE).append(REGEX_COMMA)
            .append(lottoNumberWithSpacesRegex())
            .append(REGEX_GROUP_END).append("{5}")
            .append(REGEX_END)
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
        return List.of(numbers.split(REGEX_COMMA));
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
