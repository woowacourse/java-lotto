import static java.util.stream.Collectors.toList;

import java.util.List;

public class LottoNumberParser {

    private static final int LOTTO_NUMBER_LENGTH = 6;
    private static final String REGEX_DELIMITER = ",";

    static final String INVALID_LOTTO_NUMBER_LENGTH_MESSAGE = "당첨 번호는 6개여야 합니다.";
    static final String DUPLICATED_LOTTO_NUMBER_MESSAGE = "중복된 당첨 번호는 허용하지 않습니다.";

    public List<Integer> parse(String numbers) {
        List<String> tokens = splitNumbers(numbers);
        List<String> trimNumbers = trimNumbers(tokens);
        List<Integer> intNumbers = toInts(trimNumbers);
        checkLottoNumber(intNumbers);
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

    private void checkLottoNumber(List<Integer> numbers) {
        checkLottoNumberLength(numbers);
        checkDuplicatedLottoNumber(numbers);
        checkLottoNumberRange(numbers);
    }

    private void checkLottoNumberLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_LENGTH_MESSAGE);
        }
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
            throw new IllegalArgumentException("당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.");
        }
    }

    private boolean hasInvalidNumberRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(this::isInvalidRange);
    }

    private boolean isInvalidRange(Integer number) {
        return number < 1 || number > 45;
    }

    private List<Integer> toInts(List<String> numbers) {
        try {
            return numbers.stream()
                    .map(Integer::valueOf)
                    .collect(toList());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("당첨 번호는 반드시 숫자여야 합니다.", exception);
        }
    }
}
