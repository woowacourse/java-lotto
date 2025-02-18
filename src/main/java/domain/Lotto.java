package domain;

import java.util.List;
import validator.LottoValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers.stream().sorted().toList();
        LottoValidator.validate(this.numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static Lotto from(List<Integer> randomNumbers) {
        List<Integer> numbers = randomNumbers;
        return new Lotto(numbers);
    }

    public boolean hasDuplicateNumber(final int bonusNumber) {
        return numbers.stream().anyMatch(number -> number == bonusNumber);
    }

    public boolean has(int number) {
        return this.numbers.contains(number);
    }

    public int getHitCountFrom(Lotto lotto) {
        int hitCount = 0;
        for (Integer number : this.numbers) {
            hitCount += isHit(lotto.numbers, number);
        }
        return hitCount;
    }

    private int isHit(List<Integer> numbers, int number) {
        if (numbers.contains(number)) {
            return 1;
        }
        return 0;
    }
}
