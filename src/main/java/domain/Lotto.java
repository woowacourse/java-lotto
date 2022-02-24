package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_NUMBER_MAXIMUM = 45;
    public static final int LOTTO_NUMBER_MINIMUM = 1;

    private static final String DELIMITER = ", ";

    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        lottoNumbers.forEach(this::validateNumberRange);
        lottoNumbers.forEach(this::validateDuplicatedNumber);
    }

    private void validateNumberRange(Integer lottoNumber) {
        if (lottoNumber > LOTTO_NUMBER_MAXIMUM || lottoNumber < LOTTO_NUMBER_MINIMUM) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicatedNumber(Integer lottoNumber) {
        if (lottoNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER));
    }

    public int countSameNumbers(List<Integer> numbers) {
        return (int) lottoNumbers.stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean checkBonus(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}
