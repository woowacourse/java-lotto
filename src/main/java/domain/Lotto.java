package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_NUMBER_MAXIMUM = 45;
    public static final int LOTTO_NUMBER_MINIMUM = 1;

    private static final String DELIMITER = ", ";

    private final List<Integer> numbers = new ArrayList<>();

    public Lotto(List<Integer> numbers) {
        for (Integer lottoNumber : numbers) {
            validateLottoNumbers(lottoNumber);
            this.numbers.add(lottoNumber);
        }
    }

    private void validateLottoNumbers(Integer number) {
        validateNumberRange(number);
        validateDuplicatedNumber(number);
    }

    private void validateNumberRange(Integer number) {
        if (number > LOTTO_NUMBER_MAXIMUM || number < LOTTO_NUMBER_MINIMUM) {
            throw new IllegalArgumentException(String.valueOf(number));
        }
    }

    private void validateDuplicatedNumber(Integer number) {
        if (numbers.contains(number)) {
            throw new IllegalArgumentException(String.valueOf(numbers));
        }
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER));
    }

    public int countSameNumbers(List<Integer> numbers) {
        return (int) this.numbers.stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean checkBonus(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
