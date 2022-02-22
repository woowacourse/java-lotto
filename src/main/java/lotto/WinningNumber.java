package lotto;

import lotto.util.StringToIntConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WinningNumber {

    private static final String DELIMITER = ",";
    private static final int WINNING_NUMBER_SIZE = 6;

    private final Set<LottoNumber> numbers = new HashSet<>();

    public WinningNumber(String input) {
        String[] inputNumbers = trim(split(input));
        addWinningNumbers(inputNumbers);
        validateSize();
    }

    private String[] split(String input) {
        return input.split(DELIMITER);
    }

    private String[] trim(String[] numbers) {
        return Arrays.stream(numbers)
                .map(String::trim)
                .toArray(String[]::new);
    }

    private void addWinningNumbers(String[] inputNumbers) {
        for (String number : inputNumbers) {
            numbers.add(new LottoNumber(StringToIntConverter.toInt(number)));
        }
    }

    private void validateSize() {
        if (numbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 중복되지 않는 " + WINNING_NUMBER_SIZE + "개의 숫자여야합니다");
        }
    }

    public Set<LottoNumber> getNumbers() {
        return numbers;
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }
}
