package view.parser;

import static java.util.stream.Collectors.toList;

import java.util.List;
import model.LottoNumbers;

public class LottoNumbersParser extends Parser<LottoNumbers> {
    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호의 형식이 잘못 되었습니다. 예) 1, 2, 3, 4, 5, 6";

    @Override
    protected LottoNumbers convert(String text) {
        List<String> tokens = splitByComma(text);
        List<String> trimmedTokens = trimEach(tokens);
        List<Integer> numbers = toIntEach(trimmedTokens);
        return new LottoNumbers(numbers);
    }

    private List<String> splitByComma(String numbers) {
        return List.of(numbers.split(REGEX_COMMA));
    }

    private List<String> trimEach(List<String> numbers) {
        return numbers.stream()
            .map(String::trim)
            .collect(toList());
    }

    private List<Integer> toIntEach(List<String> numbers) {
        return numbers.stream()
            .map(Integer::valueOf)
            .collect(toList());
    }

    @Override
    protected String regex() {
        return new StringBuilder(REGEX_BEGINNING)
            .append(numberWithSpacesRegex())
            .append(REGEX_GROUP_BEGINNING)
            .append(REGEX_SPACE).append(REGEX_COMMA)
            .append(numberWithSpacesRegex())
            .append(REGEX_GROUP_END).append(repeatRegex(5))
            .append(REGEX_END)
            .toString();
    }

    @Override
    protected String errorMessage() {
        return INVALID_LOTTO_NUMBER_FORMAT_MESSAGE;
    }
}
