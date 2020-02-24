package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import lotto.exception.InvalidLottoException;

public class Lotto {
    public static final int COUNT_LOTTO_NUMBER = 6;
    private final List<Number> numbers;

    public Lotto(List<Number> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Number> numbers) {
        checkSize(numbers);
        checkDuplication(numbers);
    }

    private void checkDuplication(List<Number> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new InvalidLottoException("로또 번호는 중복 될 수 없습니다.");
        }
    }

    private void checkSize(List<Number> numbers) {
        if (numbers.size() != COUNT_LOTTO_NUMBER) {
            throw new InvalidLottoException("로또는 6개의 수를 가져야 합니다.");
        }
    }

    public int compare(Lotto winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    public List<Number> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
