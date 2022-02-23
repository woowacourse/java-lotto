package view;

import static java.util.stream.Collectors.toList;

import java.util.List;
import view.LottoNumberFormatValidator;

public class LottoNumberParser {

    private static final String REGEX_DELIMITER = ",";

    private LottoNumberFormatValidator formatValidator = new LottoNumberFormatValidator();

    public List<Integer> parse(String numbers) {
        formatValidator.validate(numbers);
        List<Integer> lottoNumbers = parseToLottoNumbers(numbers);
        return lottoNumbers;
    }

    private List<Integer> parseToLottoNumbers(String numbers) {
        List<String> tokens = splitNumbers(numbers);
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
