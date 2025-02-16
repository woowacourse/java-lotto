package model.numbers;

import java.util.List;

public class LottoNumbers {

    private static final int VALID_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        validNumbers(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    public boolean contains(int comparedValue) {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .anyMatch(number -> number == comparedValue);
    }

    private void validNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != VALID_SIZE) {
            throw new IllegalArgumentException("[ERROR] 번호는 6개여야 합니다.");
        }
    }
}
