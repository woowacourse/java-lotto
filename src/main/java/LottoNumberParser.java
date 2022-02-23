import static java.util.stream.Collectors.toList;

import java.util.List;

public class LottoNumberParser {

    private static final String REGEX_DELIMITER = ",";

    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호는 반드시 6개의 숫자여야 합니다.";
    static final String DUPLICATED_LOTTO_NUMBER_MESSAGE = "중복된 당첨 번호는 허용하지 않습니다.";
    static final String INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.";

    private LottoNumberFormatValidator formatValidator = new LottoNumberFormatValidator();
    private LottoNumberValidator lottoNumberValidator = new LottoNumberValidator();

    public List<Integer> parse(String numbers) {
        formatValidator.validate(numbers);
        List<Integer> lottoNumbers = parseToLottoNumbers(numbers);
        checkLottoNumberElements(lottoNumbers);
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

    private void checkLottoNumberElements(List<Integer> numbers) {
        checkDuplicatedLottoNumber(numbers);
        checkLottoNumberRange(numbers);
    }

    private void checkDuplicatedLottoNumber(List<Integer> numbers) {
        if (hasDuplicatedNumber(numbers)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBER_MESSAGE);
        }
    }

    private boolean hasDuplicatedNumber(List<Integer> numbers) {
        return getDistinctSize(numbers) != numbers.size();
    }

    private long getDistinctSize(List<Integer> numbers) {
        return numbers.stream()
            .distinct()
            .count();
    }

    private void checkLottoNumberRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            lottoNumberValidator.validate(number);
        }
    }
}
