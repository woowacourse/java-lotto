package domain;

import java.util.List;

public class LottoNumbers {
    public static int LOTTO_SIZE = 6;
    public static int LOTTO_MIN_NUMBER = 1;
    public static int LOTTO_MAX_NUMBER = 45;
    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        numbers.forEach(this::validateLottoNumberRange);
        validateDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    private void validateLottoNumberRange(Integer number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하여야 합니다.");
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getSize() {
        return numbers.size();
    }
}
