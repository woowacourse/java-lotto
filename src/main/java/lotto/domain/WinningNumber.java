package lotto.domain;

import lotto.util.StringToIntConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class WinningNumber {

    private static final String DELIMITER = ",";

    private final LottoNumbers numbers;

    public WinningNumber(String input) {
        String[] inputNumbers = trim(split(input));
        numbers = new LottoNumbers(getWinnings(inputNumbers));
    }

    private String[] split(String input) {
        return input.split(DELIMITER);
    }

    private String[] trim(String[] numbers) {
        return Arrays.stream(numbers)
                .map(String::trim)
                .toArray(String[]::new);
    }

    private List<LottoNumber> getWinnings(String[] inputNumbers) {
        return Arrays.stream(inputNumbers)
                .map(StringToIntConverter::toInt)
                .map(LottoNumber::new)
                .collect(toList());
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public Set<LottoNumber> toSet() {
        return new HashSet<>(numbers.getLottoNumbers());
    }
}
