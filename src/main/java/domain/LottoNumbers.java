package domain;

import java.util.List;

public record LottoNumbers(List<Integer> numbers) {
    public static int LOTTO_SIZE = 6;
    public static int LOTTO_MIN_NUMBER = 1;
    public static int LOTTO_MAX_NUMBER = 45;

    public LottoNumbers {
        numbers.forEach(this::validateLottoNumberRange);
        validateDuplicateNumber(numbers);
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

    public int getSize() {
        return numbers.size();
    }
}
