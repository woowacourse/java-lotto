package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = getSortedNumbers(numbers);
    }

    public static void validateNumberRange(final int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    "로또 번호는 %d ~ %d 사이여야 합니다.".formatted(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
    }

    public boolean contains(final int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private List<Integer> getSortedNumbers(final List<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }

    private void validateNumbers(final List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE + "개의 고유한 번호를 입력해야 합니다.");
        }

        for (final int winningNumber : winningNumbers) {
            validateNumberRange(winningNumber);
        }
    }
}
