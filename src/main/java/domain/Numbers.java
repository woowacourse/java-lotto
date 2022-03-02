package domain;

import java.util.List;

public class Numbers {

    private static final String NUMBERS_LENGTH_ONLY_SIX = "[ERROR] 로또 번호는 6자리여야 합니다.";
    private static final String NUMBERS_DUPLICATE_MESSAGE = "[ERROR] 로또 번호는 중복되지 않는 6개의 숫자여야 합니다.";
    private static final int LENGTH_STANDARD = 6;

    private final List<LottoNumber> numbers;

    public Numbers(List<LottoNumber> numbers) {
        checkNumbersLength(numbers);
        checkDuplicatedNumbers(numbers);
        this.numbers = numbers;
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    private void checkNumbersLength(List<LottoNumber> numbers) {
        if (numbers.size() != LENGTH_STANDARD) {
            throw new IllegalArgumentException(NUMBERS_LENGTH_ONLY_SIX);
        }
    }

    private void checkDuplicatedNumbers(List<LottoNumber> numbers) {
        int duplicatedCount = (int) numbers.stream().distinct().count();
        if (duplicatedCount != numbers.size()) {
            throw new IllegalArgumentException(NUMBERS_DUPLICATE_MESSAGE);
        }
    }
}
