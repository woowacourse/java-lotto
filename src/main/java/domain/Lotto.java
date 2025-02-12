package domain;

import java.util.Set;

public class Lotto {

    public static final int NUMBERS_SIZE = 6;

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(Set<Integer> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }
}
