import static java.util.stream.Collectors.toList;

import java.util.List;

public class LottoNumberParser {

    private static final String REGEX_DELIMITER = ",";
    private static final String REGEX_LOTTO_NUMBER_FORMAT = "^\\s*[0-9]{1,2}\\s*(\\s*,\\s*[0-9]{1,2}\\s*){5}$";

    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호는 반드시 6개의 숫자여야 합니다.";
    static final String DUPLICATED_LOTTO_NUMBER_MESSAGE = "중복된 당첨 번호는 허용하지 않습니다.";
    static final String INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.";

    public List<Integer> parse(String numbers) {
        checkLottoNumberFormat(numbers);
        List<Integer> lottoNumbers = parseToLottoNumbers(numbers);
        checkLottoNumberElements(lottoNumbers);
        return lottoNumbers;
    }

    private void checkLottoNumberFormat(String numbers) {
        if (!numbers.matches(REGEX_LOTTO_NUMBER_FORMAT)) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
        }
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
        if (hasInvalidNumberRange(numbers)) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE_MESSAGE);
        }
    }

    private boolean hasInvalidNumberRange(List<Integer> numbers) {
        return numbers.stream()
            .anyMatch(this::isInvalidRange);
    }

    private boolean isInvalidRange(Integer number) {
        return number < 1 || number > 45;
    }
}
